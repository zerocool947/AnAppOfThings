package com.poorfellow.agameofthings.chromecast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class ConnectionFailedListener implements OnConnectionFailedListener {

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        //teardown method
    }
}
