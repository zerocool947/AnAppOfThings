package com.poorfellow.agameofthings.chromecast;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.Cast.ApplicationConnectionResult;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaInfo.Builder;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult;
import com.google.android.gms.cast.RemoteMediaPlayer.OnMetadataUpdatedListener;
import com.google.android.gms.cast.RemoteMediaPlayer.OnStatusUpdatedListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.IOException;

/**
 * Created by David on 3/19/14.
 */
public class ConnectionCallbacks implements GoogleApiClient.ConnectionCallbacks {

    private ChromecastHelper mChromecastHelper;
    private boolean waitingOnReconnect;
    private RemoteMediaPlayer mRemoteMediaPlayer;
    private GoogleApiClient mApiClient;

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("STATUS", "Connection Acquired");
        mChromecastHelper = ChromecastHelper.getInstance();
        mApiClient = mChromecastHelper.getApiClient();

        try {
            Cast.CastApi.launchApplication(mApiClient, mChromecastHelper.DEV_APP_ID, false)
            .setResultCallback(new ResultCallback<ApplicationConnectionResult>() {
                @Override
                public void onResult(ApplicationConnectionResult result) {
                    Status status = result.getStatus();
                    if (status.isSuccess()) {
                        ApplicationMetadata appMetadata = result.getApplicationMetadata();
                        String sessionId = result.getSessionId();
                        String appStatus = result.getApplicationStatus();
                        boolean wasLaunches = result.getWasLaunched();
                        mChromecastHelper.openMediaChannel();
                        mChromecastHelper.displayPhoto();
                    } else {
                        //teardown method
                    }
                }
            });

        } catch (Exception e) {
            Log.e("ERROR", "Failed to launch application", e);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        //status upkeep
    }

}
