package com.poorfellow.agameofthings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.poorfellow.agameofthings.bluetooth.BluetoothHandler;

/**
 * Created by David on 3/2/14.
 */
public class GameLobbyActivity extends Activity {
    BluetoothHandler btHandler;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lobby);

        btHandler = BluetoothHandler.getInstance(this);
        btHandler.enableBluetooth();
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }*/

    public void OnDestroy() {
        super.onDestroy();

        btHandler.destroyHandler();
    }
}