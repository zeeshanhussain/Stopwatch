package com.example.android.stopwatch;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.stopwatch.StopwatchService.LocalBinder;


public class MainActivity extends AppCompatActivity {
    com.example.android.stopwatch.Chronometer mChronometer;
    private Button mRecordButton, mStopButton, mResetButton;
    boolean mBounded;
    StopwatchService mStopwatchService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChronometer = (com.example.android.stopwatch.Chronometer) findViewById(R.id.chrono);
        mRecordButton = (Button) findViewById(R.id.btnStart);
        mStopButton = (Button) findViewById(R.id.btnStop);
        mResetButton = (Button) findViewById(R.id.btnReset);
        final Intent intent = new Intent(MainActivity.this, StopwatchService.class);

        mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long wew = mChronometer.getTimeElapsed();
              mChronometer.setBase(SystemClock.elapsedRealtime() - wew);
               mChronometer.start();
                startService(intent);
            }

            });
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChronometer.stop();
                mStopwatchService.updateNotification("Stopwatch Stopped");
            }

        });
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChronometer.setBase(SystemClock.elapsedRealtime());
       mChronometer.stop();
                mStopwatchService.stopStopWatch();
                stopService(intent);}

        });

                                         }
    @Override
    protected void onStart() {
        super.onStart();
        Intent mIntent = new Intent(this, StopwatchService.class);
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);
    };

    ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceDisconnected(ComponentName name) {
            mBounded = false;
            mStopwatchService = null;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            mBounded = true;
            LocalBinder mLocalBinder = (LocalBinder)service;
            mStopwatchService = mLocalBinder.getServerInstance();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if(mBounded) {
            unbindService(mConnection);
            mBounded = false;
        }
    };
}

