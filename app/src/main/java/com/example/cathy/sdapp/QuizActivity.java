package com.example.cathy.sdapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class QuizActivity extends AppCompatActivity {

    // initialize private vars
    private View mContentView;
    private TextView quizQuestionField, quizTimer;
    private View mControlsView;
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;

    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // set vars
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Intent intent = getIntent();
        int category = intent.getIntExtra("CATEGORY", 0);
        quizQuestionField = (TextView) findViewById(R.id.quiz_question);
        quizTimer = (TextView) findViewById(R.id.quiz_timer);

        // hide status bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        SensorEventListener mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
               /* zTilt: positive -> screen facing up
                         zero -> vertical
                         negative -> screen facing down
                */
                int rawZ = (int) event.values[2];
                int zTilt = (int) rawZ / 3 ;
                zTilt %=2;
                quizQuestionField.setText(zTilt+" "+rawZ);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        mSensorManager.registerListener(mSensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_UI);

        CountDownTimer countdown = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                quizTimer.setText("" + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                mControlsView.setVisibility(View.INVISIBLE);
                mContentView.setBackgroundColor(Color.RED);
                changeQuestion("Time's Up!");
            }
        }.start();

        quizQuestionField.setText(category + "");
        //TODO: make randomized question code here

        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }


    private void changeQuestion(String text){
        Animation flipIn = AnimationUtils.loadAnimation(this, R.anim.flip_in);
        Animation flipOut = AnimationUtils.loadAnimation(this, R.anim.flip_out);
        quizQuestionField.setAnimation(flipOut);
        quizQuestionField.setText(text);
        quizQuestionField.startAnimation(flipIn);
    }

}
