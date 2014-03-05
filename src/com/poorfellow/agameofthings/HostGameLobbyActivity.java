package com.poorfellow.agameofthings;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;
import com.poorfellow.agameofthings.bluetooth.BluetoothHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 3/2/14.
 */
public class HostGameLobbyActivity extends Activity {
    BluetoothHandler btHandler;
    private Map<String, String> playerMap;
    private BroadcastReceiver scanChangedReceiver;
    private IntentFilter scanChangedFilter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lobby);
        final ScrollView playersScroll = (ScrollView) findViewById(R.id.joiningPlayersScollView);
        playerMap = new HashMap<String, String>();

        btHandler = BluetoothHandler.getInstance(this);
        Log.d("STATUS", "Created Handler, enabling bluetooth.");

        btHandler.enableBluetooth();

        Log.d("STATUS", "Enbled bluetooth, opening queue.");
        btHandler.openQueue(3600);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("STATUS", "Discoverable status is Status is : " + btHandler.isDiscoverable());

        scanChangedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };


        /*actionFoundBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    playerMap.put(device.getAddress(), device.getName());
                    TextView newPlayerText = new TextView(context);
                    newPlayerText.setText(device.getName());
                    playersScroll.addView(newPlayerText);
                }
            }
        };

        destroyBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothHandler.STOP_DISCOVERING.equals(action)) {
                    btHandler.closeQueue();
                }
            }
        };

        foundDeviceFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(actionFoundBroadcastReceiver, foundDeviceFilter);

        destroyDiscoveryFilter = new IntentFilter(BluetoothHandler.STOP_DISCOVERING);
        registerReceiver(destroyBroadcastReceiver, destroyDiscoveryFilter);*/

        //I want to be the discoverable one


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

        btHandler.destroyHandler();
    }
}