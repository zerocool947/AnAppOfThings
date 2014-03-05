package com.poorfellow.agameofthings.bluetooth;


import android.bluetooth.BluetoothServerSocket;
import android.content.Context;

/**
 * Created by David on 3/3/14.
 */
public class BluetoothServerHandler {

    private static BluetoothServerHandler bluetoothServerHandler;

    public static BluetoothServerHandler getInstance(final Context context) {
        return null;
    }

    private final Context context;
    private final BluetoothServerSocket bluetoothServerSocket;

    public BluetoothServerHandler (Context context, BluetoothServerSocket bluetoothServerSocket) {
        this.context = context;
        this.bluetoothServerSocket = bluetoothServerSocket;

    }
}
