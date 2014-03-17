package com.poorfellow.agameofthings;

import android.support.v7.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.MediaRouteButton;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.RouteInfo;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.poorfellow.agameofthings.chromecast.ChromecastHelper;
import android.support.v7.app.MediaRouteActionProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 3/2/14.
 */
public class HostGameLobbyActivity
        extends ActionBarActivity
        implements ConnectionCallbacks, OnConnectionFailedListener {
    private Map<String, String> playerMap;
    private BroadcastReceiver scanChangedReceiver;
    private IntentFilter scanChangedFilter;
    private MediaRouter mediaRouter;
    private MediaRouteSelector mediaRouteSelector;
    private ChromecastHelper chromecastHelper;
    private CastDevice selectedDevice;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lobby);
        final ScrollView playersScroll = (ScrollView) findViewById(R.id.joiningPlayersScollView);
        playerMap = new HashMap<String, String>();
        chromecastHelper = ChromecastHelper.getInstance(this);
        mediaRouteSelector = chromecastHelper.getMediaRouteSelector();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        actionBar.show();




        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scanChangedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("STATUS", "Inside on create options menu");
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.media_router_action_bar, menu);
        MenuItem mediaRouteMenuItem = menu.findItem(R.id.media_route_menu_item);
        MediaRouteButton mediaRouteButton = (MediaRouteButton) mediaRouteMenuItem.getActionView();
        if (mediaRouteButton == null) {
            Log.d("STATUS", "Menu Button is null");
        }

        if (mediaRouteMenuItem == null) {
            Log.d("STATUS", "Route Menu Item is null");
        }

        MediaRouteActionProvider mediaActionProvider = (MediaRouteActionProvider) MenuItemCompat.getActionProvider(mediaRouteMenuItem);
        if (mediaActionProvider == null) {
            Log.d("STATUS", "action provider is null");
        }
        mediaActionProvider.setRouteSelector(mediaRouteSelector);
        Log.d("STATUS", "The media button is enabled? " + mediaActionProvider.getMediaRouteButton().isEnabled());
        Log.d("STATUS", "The media button is in layout? " + mediaActionProvider.getMediaRouteButton().isInLayout());

        return true;
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }*/

    public void onPause() {
        super.onPause();


    }

    public void onResume() {
        super.onResume();


    }

    public void OnDestroy() {
        super.onDestroy();

    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private class MediaRouterCallback extends MediaRouter.Callback {

        @Override
        public void onRouteSelected(MediaRouter router, RouteInfo info) {
            selectedDevice = CastDevice.getFromBundle(info.getExtras());
            //maybe get the id, idk
        }

        @Override
        public void onRouteUnselected(MediaRouter router, RouteInfo info) {
            //make a teardown method
            selectedDevice = null;

        }
    }

}