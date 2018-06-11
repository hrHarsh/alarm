package com.example.android.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    final static int REQUEST_CODE=1;
    final static long TIME_INTERVAL=System.currentTimeMillis()+10*1000;
    MediaPlayer mediaPlayer;

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.immigrant_song);

        Intent intent=new Intent(this,broadcastReceiver.class);

        PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),REQUEST_CODE,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP,TIME_INTERVAL,pendingIntent);
        mediaPlayer.start();

    }
}
