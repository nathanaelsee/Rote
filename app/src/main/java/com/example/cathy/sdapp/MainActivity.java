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

public class MainActivity extends AppCompatActivity{

    private SensorManager mSensorManager;
    private Activity mActivity;
    private float mAccelX = 0;
    private float mAccelY = 0;
    private float mAccelZ = 0;
    private float mGround = 0;
    public Vibrator v;

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


        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

    }

    private void goToSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
