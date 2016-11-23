package com.example.android.stopwatch;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;


/**
 * Created by zeeshan on 22/11/16.
 */

public class StopwatchService extends Service{
    IBinder mBinder = new LocalBinder();


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public StopwatchService getServerInstance() {
            return StopwatchService.this;
        }
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

            startForeground(1, createNotification("Stopwatch Running"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopStopWatch() {
        startForeground(0, createNotification("K"));
    }

    private Notification createNotification(String text) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle(text)
                        .setContentText("Click here to open app")
                        .setSmallIcon(android.R.drawable.star_on)
                        .setOngoing(true);

        mBuilder.setContentIntent(PendingIntent.getActivities(getApplicationContext(), 0,
                new Intent[]{new Intent(getApplicationContext(), MainActivity.class)}, 0));

        return mBuilder.build();
    }

    public void updateNotification(String text) {
        Notification notification = createNotification(text);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, notification);
    }




}
