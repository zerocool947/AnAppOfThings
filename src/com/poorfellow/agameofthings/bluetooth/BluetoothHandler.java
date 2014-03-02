package com.poorfellow.agameofthings.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by David on 3/2/14.
 */
public class BluetoothHandler extends Thread {

    private static BluetoothHandler bluetoothHandler;

    private final static int REQUEST_ENABLE_BT = 1;

    public static BluetoothHandler getInstance(final Context context) {
        if (bluetoothHandler == null) {
            bluetoothHandler = new BluetoothHandler(context);
        }
        return bluetoothHandler;
    }

    private final Context context;
    private final BluetoothAdapter bluetoothAdapter;
    private Map<String, String> playerMap;
    private boolean discoveryMode = false;

    public BluetoothHandler (Context context) {
        this.context = context;
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.playerMap = new HashMap<String, String>();
    }

    public boolean checkIfBluetoothSupported() {
        if (bluetoothAdapter == null) {
            return false;
        }

        return true;
    }

    public void enableBluetooth() {
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBt = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            ((Activity) context).startActivityForResult(enableBt, REQUEST_ENABLE_BT);
        }
    }

    public void openQueue() {
        discoveryMode = true;
        while (discoveryMode) {
            if (bluetoothAdapter.startDiscovery()) {
                final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                            playerMap.put(device.getAddress(), device.getName());
                        }
                    }
                };

                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                context.registerReceiver(broadcastReceiver, filter);

            }
        }
    }

    public void destroyHandler() {
        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }
    }


}
