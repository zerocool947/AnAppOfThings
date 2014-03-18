package com.poorfellow.agameofthings.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by David on 3/2/14.
 */
public class BluetoothHandler {

    private static BluetoothHandler bluetoothHandler;

    private final static int REQUEST_ENABLE_BT = 1;
    public final static String STOP_DISCOVERING = "com.poorfellow.agameofthings.bluetooth.STOP_DISCOVERING";

    public static BluetoothHandler getInstance(final Context context) {
        if (bluetoothHandler == null) {
            bluetoothHandler = new BluetoothHandler(context);
        }
        return bluetoothHandler;
    }

    private final Context context;
    private final BluetoothAdapter bluetoothAdapter;
    private boolean discoveryMode = false;

    public BluetoothHandler (Context context) {
        this.context = context;
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
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

    /**
     *
     * @param duration Anything outside of 0 and 3600 sets to 120. 0 means indefinite. So far haven't found a way to close from that state 3/3/2014
     */
    public void openQueue(int duration) {
        Log.d("STATUS", "attempting to open discovery.");
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, duration);
        context.startActivity(discoverableIntent);

    }

    public boolean startDiscovery() {
        if (!bluetoothAdapter.isDiscovering()) {
            return bluetoothAdapter.startDiscovery();
        }

        return true;
    }

    public void closeQueue() {
        Log.d("STATUS", "attempting to close discovery.");
    }

    public void destroyHandler() {
        closeQueue();
    }

    public boolean isDiscoverable() {
        if (bluetoothAdapter.getScanMode() == BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            return true;
        }
        else {
            return false;
        }
    }


}
