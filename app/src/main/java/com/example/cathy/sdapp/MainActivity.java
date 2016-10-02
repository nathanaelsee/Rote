package com.example.cathy.sdapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.os.Vibrator;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Activity mActivity;
    private float mAccelX = 0;
    private float mAccelY = 0;
    private float mAccelZ = 0;
    private float mGround = 0;
    public Vibrator v;

//    TextView tvHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSecondActivity();
            }
        });

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);

        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

//        tvHeading = (TextView) findViewById(R.id.tvHeading);
    }

    private void goToSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    private final SensorEventListener mSenseAcc = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //do later
        }

        public void onSensorChanged(int sensor, float[] values) {
//            mAccelX = values[0];
//            mAccelY = values[1];
//            mAccelZ = values[2];
        }

        public void onSensorChanged(SensorEvent senEvent) {
//            mAccelX = senEvent.values[0];
//            mAccelY = senEvent.values[1];
//            mAccelZ = senEvent.values[2];
//
//            if (mAccelZ > mGround+2) {
//                v.vibrate(100);
//            }
        }
    };

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //do later
    }


    public void onSensorChanged(int sensor, float[] values) {
        mAccelX = values[0];
        mAccelY = values[1];
        mAccelZ = values[2];
    }

    public void onSensorChanged(SensorEvent senEvent) {
        mAccelX = senEvent.values[0];
        mAccelY = senEvent.values[1];
        mAccelZ = senEvent.values[2];

        if (mAccelZ > mGround+2) {
            v.vibrate(100);

//            tvHeading.setText("Rotated");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this,   mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }
}
