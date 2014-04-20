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
                    } else {
                        //teardown method
                    }
                }
            });
            Log.d("STATUS", "Creating Media Channel");
            mRemoteMediaPlayer = new RemoteMediaPlayer();

            mRemoteMediaPlayer.setOnStatusUpdatedListener(new OnStatusUpdatedListener() {

                @Override
                public void onStatusUpdated() {
                    MediaStatus mediaStatus = mRemoteMediaPlayer.getMediaStatus();
                    boolean isPlaying = mediaStatus.getPlayerState() == MediaStatus.PLAYER_STATE_PLAYING;
                }
            });

            mRemoteMediaPlayer.setOnMetadataUpdatedListener(new OnMetadataUpdatedListener() {
                @Override
                public void onMetadataUpdated() {
                    MediaInfo mediaInfo = mRemoteMediaPlayer.getMediaInfo();
                    MediaMetadata metadata = mediaInfo.getMetadata();
                }
            });
            try {
                Cast.CastApi.setMessageReceivedCallbacks(mApiClient, mRemoteMediaPlayer.getNamespace(), mRemoteMediaPlayer);
            } catch (IOException e) {
                Log.e("ERROR", "Error creating media channel", e);
            }
            mRemoteMediaPlayer.requestStatus(mApiClient).setResultCallback(new ResultCallback<MediaChannelResult>() {
                @Override
                public void onResult(MediaChannelResult result) {
                    if (!result.getStatus().isSuccess()) {
                        Log.e("ERROR", "Failed to request status");
                    }
                }
            });
            Log.d("STATUS", "Media Channel opened");

            MediaMetadata mediaMetadata = new MediaMetadata(MediaMetadata.MEDIA_TYPE_PHOTO);
            mediaMetadata.putString(MediaMetadata.KEY_TITLE, "My Photo");
            MediaInfo mediaInfo = new Builder("/DCIM/Camera/20140419_160800.jpg")
                    .setContentType("image/jpeg")
                    .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED)
                    .setMetadata(mediaMetadata)
                    .build();

            try {
                mRemoteMediaPlayer.load(mApiClient, mediaInfo, true).setResultCallback(new ResultCallback<MediaChannelResult>() {
                    @Override
                    public void onResult(MediaChannelResult result) {
                        if (result.getStatus().isSuccess()) {
                            Log.d("STATUS", "Media loaded");
                        }
                    }
                });
            } catch(IllegalStateException e) {
                Log.e("ERROR", "Problem occurred with media during loading", e);
            } catch(Exception e) {
                Log.e("ERROR", "Problem opening media during loading", e);
            }
        } catch (Exception e) {
            Log.e("ERROR", "Failed to launch application", e);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        //status upkeep
    }

}
