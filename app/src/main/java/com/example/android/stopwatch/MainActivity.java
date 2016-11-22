package com.example.android.stopwatch;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    com.example.android.stopwatch.Chronometer mChronometer;
    private Button mRecordButton, mStopButton, mResetButton;
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
            }

        });
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChronometer.setBase(SystemClock.elapsedRealtime());
       mChronometer.stop();
                stopService(intent);}

        });

                                         }

}

