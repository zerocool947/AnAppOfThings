package com.poorfellow.agameofthings.chromecast;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.util.Log;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.Cast.Listener;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;


/**
 * Created by David on 3/15/14.
 */
public class ChromecastHelper {

    private static final String DIALOG_ERROR = "dialog_error";
    private static final int REQUEST_RESOLVE_ERROR = 1001;

    private static final String DEV_APP_ID = "2C18770F";

    private GoogleApiClient mApiClient;
    private final Context mContext;
    private MediaRouter mMediaRouter;
    private MediaRouteSelector mMediaRouteSelector;
    private Cast.Listener mCastClientListener;
    private CastDevice mConnectedDevice;

    private static ChromecastHelper mChromecastHelper = null;

    public static ChromecastHelper getInstance(final Context context) {
        if (mChromecastHelper == null) {
            mChromecastHelper = new ChromecastHelper(context);
            return mChromecastHelper;
        }
        else {
            return mChromecastHelper;
        }
    }

    public ChromecastHelper (Context context) {
        this.mContext = context;
        this.mCastClientListener = new Listener() {
            @Override
            public void onApplicationStatusChanged() {
                //do something
            }

            @Override
            public void onVolumeChanged() {
                //do something
            }

            @Override
            public void onApplicationDisconnected(int errorCode) {
                //teardown method
            }
        };



        this.mMediaRouter = MediaRouter.getInstance(mContext.getApplicationContext());
        mMediaRouteSelector = new MediaRouteSelector.Builder()
                .addControlCategory(CastMediaControlIntent.categoryForCast(DEV_APP_ID))
                .build();

    }

    public void connect() {
        mApiClient.connect();
        Log.d("STATUS", "Chromecast API Connection Connected.");
    }

    public void disconnect() {
        mApiClient.disconnect();
        Log.d("STATUS", "Chromecast API Connection Disconnected.");
    }

    public boolean isConnected() {
        return mApiClient.isConnected();
    }

    public void handleDialogError(int errorCode) {
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment(mContext);
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        dialogFragment.show(((FragmentActivity) mContext).getFragmentManager(), "errordialog");
    }

    public MediaRouteSelector getMediaRouteSelector() {
        return mMediaRouteSelector;
    }

    public MediaRouter getMediaRouter() {
        return mMediaRouter;
    }

    public void instantiateApiClient(CastDevice castDevice) {
        this.mApiClient = new GoogleApiClient.Builder(mContext)
                .addApi(Cast.API, Cast.CastOptions.builder(castDevice, mCastClientListener).build())
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) mContext)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) mContext)
                .build();
        Log.d("STATUS", "Chromecast API Connection Built.");
    }

    public void instantiateApiClient() {
        instantiateApiClient(mConnectedDevice);
    }

    public void setConnectedDevice(CastDevice connectedDevice) {
        this.mConnectedDevice = connectedDevice;
    }

    public static class ErrorDialogFragment extends DialogFragment {
        private final Context pContext;

        public ErrorDialogFragment(final Context context) {
            pContext = context;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the error code and retrieve the appropriate dialog
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
            return GooglePlayServicesUtil.getErrorDialog(errorCode,
                    getActivity(), REQUEST_RESOLVE_ERROR);
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            //setup callback methods
        }
    }
}
