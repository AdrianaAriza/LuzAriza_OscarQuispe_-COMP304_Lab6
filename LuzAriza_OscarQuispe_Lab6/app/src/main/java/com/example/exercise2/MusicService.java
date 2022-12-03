package com.example.exercise2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import java.security.Provider;

public class MusicService extends Service {

    MediaPlayer player;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("PLAY", "PLAYING MUSIC");
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("PLAY", "STOP PLAYING MUSIC");
        super.onDestroy();
        player.stop();
        player = null;
    }

}
