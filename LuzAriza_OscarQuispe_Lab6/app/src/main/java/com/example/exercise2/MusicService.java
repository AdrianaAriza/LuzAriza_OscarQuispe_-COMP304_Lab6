package com.example.exercise2;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import java.util.Objects;


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
        SharedPreferences myPref=getSharedPreferences("PrefFile", 0);
        String sound = myPref.getString("sound", "");

        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        if(Objects.equals(sound, "alarm")){
            Log.d("ALARM", "alarm");
            player = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);

        }
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        player.stop();
        player = null;
    }
}


