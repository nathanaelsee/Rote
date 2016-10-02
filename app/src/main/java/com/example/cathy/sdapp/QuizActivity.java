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

import java.util.Arrays;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {

    // initialize private vars
    private View mContentView;
    private TextView quizQuestionField, quizTimer, quizScore, cardDone, gameOver, gameWon;
    private View mControlsView;
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private Boolean finished = false;
    private float startPos = 0;
    private int index = 0, maxIndex, score = -1;
    private boolean[] doneIndexes;
    public String[] cat1 = {"Object",
            "Class",
            "Inheritance",
            "Superclass",
            "Subclass",
            "Member",
            "Method",
            "Getters and Setters",
            "Accessors and Mutators",
            "Public",
            "Private",
            "Protected"};
    public String[] cat2 = {"Insertion Best",
            "Insertion Worst",
            "Selection Sort Best",
            "Selection Sort Worst",
            "Bubble Sort Worst",
            "Bubble Sort Best",
            "Quicksort Best",
            "Quicksort Worst",
            "Merge Sort Best",
            "Binary Search Worst",
            "Linear Search Worst",
            "Linear Search Best"};
    public String[] cat3 = {"Recursion",
            "Recursive method",
            "Base case",
            "Termination condition",
            "Infinite recursion",
            "Recursive call",
            "Levels of recursion",
            "System stack",
            "Tail recursive method",
            "Tree Recursion",
            "Rule 1",
            "Rule 2"};
    public String[] cat4 = {"Artificial Intelligence",
            "Breadth First Search",
            "Genetic Algorithms",
            "Large Neighborhood Search",
            "Local Search",
            "Long Term Memory",
            "Simulated Annealing",
            "Short Term Memory",
            "Tabu Search",
            "Operation Research",
            "Explicit memory",
            "Attributive memory"};
    public String[] cat5 = {"Inheritance",
            "Extend",
            "Superclass",
            "Subclass",
            "Override",
            "Overload",
            "Super",
            "Abstract class",
            "Polymorphic method",
            "Static binding",
            "Class cast exception",
            "Interface"};
    public String[] cat6 = {"Git init",
            "Git status",
            "Git add",
            "Git diff",
            "Git commit",
            "Git log",
            "Git merge branch_name",
            "Git branch –d branch_name",
            "Git clone",
            "Git remote –v",
            "Git fetch",
            "Git merge origin/master"};
    public String[] cat7 = {"Random access machine",
            "Step",
            "Best-case running time",
            "Worst-case running time",
            "Asymptotic notation",
            "Big O notation",
            "Tight bound",
            "Constant complexity",
            "Logarithmic complexity",
            "Linear complexity",
            "Polynomial complexity",
            "Exponential complexity"};
    public String[] cat8 = {"Byte",
            "Short",
            "Int",
            "Long",
            "Float",
            "Double",
            "Char",
            "Boolean",
            "Constant",
            "Floating point data types",
            "Expression",
            "Primitive"};
    public String[][] categories = {cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8};
    boolean tiltReset = true;

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
        final int category = intent.getIntExtra("CATEGORY", 0) - 1;
        maxIndex = categories[category].length;
        doneIndexes = new boolean[maxIndex];
        Arrays.fill(doneIndexes, false);


        quizQuestionField = (TextView) findViewById(R.id.quiz_question);
        cardDone = (TextView) findViewById(R.id.green_screen);
        gameOver = (TextView) findViewById(R.id.game_lost);
        gameWon = (TextView) findViewById(R.id.game_won);
        cardDone.setVisibility(View.INVISIBLE);
        gameOver.setVisibility(View.INVISIBLE);
        gameWon.setVisibility(View.INVISIBLE);
        quizTimer = (TextView) findViewById(R.id.quiz_timer);
        quizScore = (TextView) findViewById(R.id.quiz_score);


        increaseScore();
        changeQuestion(randomQuestion(category));

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
                int zTilt = rawZ / 5;

                if(!tiltReset && zTilt == 0 && index < maxIndex) {tiltReset=true;}

                final Handler handler = new Handler();

                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        cardDone.setVisibility(View.INVISIBLE);
                        mControlsView.setVisibility(View.VISIBLE);
                        increaseScore();
                    }
                };

                if (!finished && zTilt < 0 && tiltReset && index < maxIndex) {
                    tiltReset = false;
                    mControlsView.setVisibility(View.INVISIBLE);
                    cardDone.setVisibility(View.VISIBLE);

                    handler.postDelayed(run, 300);
                    changeQuestion(randomQuestion(category));
                }

                if (index == maxIndex) {
                    mControlsView.setVisibility(View.INVISIBLE);
                    gameWon.setVisibility(View.VISIBLE);
                    finished = true;
                    gameWon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        mSensorManager.registerListener(mSensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_UI);

        CountDownTimer countdown = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                quizTimer.setText("" + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                mControlsView.setVisibility(View.INVISIBLE);
                finished = true;
                gameOver.setVisibility(View.VISIBLE);
                gameOver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }.start();

        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private String randomQuestion(int category) {
        Random random = new Random();
        int rand = random.nextInt(maxIndex);
        while(doneIndexes[rand]){
            rand = random.nextInt(maxIndex);
        }
        index++;
        doneIndexes[rand] = true;
        return categories[category][rand];
    }

    private void changeQuestion(String text){
        Animation flipIn = AnimationUtils.loadAnimation(this, R.anim.flip_in);
        Animation flipOut = AnimationUtils.loadAnimation(this, R.anim.flip_out);
        //quizQuestionField.setAnimation(flipOut);
        quizQuestionField.setText(text);
        //quizQuestionField.startAnimation(flipIn);
    }

    private void increaseScore(){
        score++;
        quizScore.setText("Score: " + score);
    }
}
