package com.example.android.stopwatch;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    com.example.android.stopwatch.Chronometer mChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChronometer = (com.example.android.stopwatch.Chronometer) findViewById(R.id.chrono);


    }

    public void onStart(View v) {

        long wew = mChronometer.getTimeElapsed();
        mChronometer.setBase(SystemClock.elapsedRealtime() - wew);
        mChronometer.start();
    }

    public void onStop(View v) {
        mChronometer.stop();

    }

    public void onReset(View v) {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.stop();

    }


}

