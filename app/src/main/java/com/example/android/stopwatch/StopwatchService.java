package com.example.android.stopwatch;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;


/**
 * Created by zeeshan on 22/11/16.
 */

public class StopwatchService extends Service{

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startStopWatch();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        stopStopWatch();
        super.onDestroy();
    }

    public void startStopWatch() {
        try {

            startForeground(1, createNotification());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopStopWatch() {
        startForeground(0, createNotification());
    }

    private Notification createNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("Stopwatch Running")
                        .setContentText("Click here to open app")
                        .setSmallIcon(android.R.drawable.star_on)
                        .setOngoing(true);

        mBuilder.setContentIntent(PendingIntent.getActivities(getApplicationContext(), 0,
                new Intent[]{new Intent(getApplicationContext(), MainActivity.class)}, 0));

        return mBuilder.build();
    }




}
