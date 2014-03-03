package com.poorfellow.agameofthings.bluetooth;

import android.util.Log;

/**
 * Created by David on 3/2/14.
 */
public class DiscoveryRunner extends Thread {

    private final BluetoothHandler btHandler;

    public DiscoveryRunner (BluetoothHandler btHandler) {
        this.btHandler = btHandler;
    }

    public void run() {
        btHandler.startDiscovery();
        Log.d("STATUS", "Discovery Open.");
    }
}
