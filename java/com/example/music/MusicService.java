package com.example.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
    MediaPlayer player;

    @Override
    public void onCreate() {
        player = MediaPlayer.create(this, R.raw.lofi_bgm);
        player.setLooping(true);
        player.setVolume(0.5f,0.5f);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        player.stop();
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }
}
