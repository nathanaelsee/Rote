package com.example.cathy.sdapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.os.Vibrator;
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
    private View topBarView, questionView;
    private TextView quizQuestionTextView, quizTimer, quizScore, flashSuccessScreen, timeOutScreen, gameWinScreen, flashSkipScreen;
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private Boolean finished = false;
    private int maxIndex, score = -1, zTilt = 0, tickCD = 0;
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
    boolean tiltReset = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // set vars
        topBarView = findViewById(R.id.fullscreen_content_controls);
        questionView = findViewById(R.id.fullscreen_content);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Intent intent = getIntent();
        final int category = intent.getIntExtra("CATEGORY", 0) - 1;
        maxIndex = categories[category].length;
        doneIndexes = new boolean[maxIndex];
        Arrays.fill(doneIndexes, false);


        quizQuestionTextView = (TextView) findViewById(R.id.quiz_question);
        flashSuccessScreen = (TextView) findViewById(R.id.green_screen);
        timeOutScreen = (TextView) findViewById(R.id.game_lost);
        gameWinScreen = (TextView) findViewById(R.id.game_won);
        flashSkipScreen= (TextView) findViewById(R.id.skip_card);
        flashSkipScreen.setVisibility(View.INVISIBLE);
        flashSuccessScreen.setVisibility(View.INVISIBLE);
        timeOutScreen.setVisibility(View.INVISIBLE);
        gameWinScreen.setVisibility(View.INVISIBLE);
        quizTimer = (TextView) findViewById(R.id.quiz_timer);
        quizScore = (TextView) findViewById(R.id.quiz_score);


        increaseScore();
        changeQuestion(randomQuestion(category, true));

        // hide status bar
        questionView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        SensorEventListener mSensorEventListener = new SensorEventListener() {
            /* zTilt: positive -> screen facing up
             *        zero -> vertical
             *        negative -> screen facing down
             */
            @Override
            public void onSensorChanged(SensorEvent event) {
                zTilt = (int) event.values[2] - 1;
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        mSensorManager.registerListener(mSensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_UI);

        CountDownTimer countdown = new CountDownTimer(60000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

                // definitions
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                quizTimer.setText("" + millisUntilFinished/1000);
                final Handler handler = new Handler();

                Runnable flashSuccess = new Runnable() {
                    @Override
                    public void run() {
                        flashSuccessScreen.setVisibility(View.INVISIBLE);
                        topBarView.setVisibility(View.VISIBLE);
                    }
                };

                Runnable flashSkip = new Runnable() {
                    @Override
                    public void run() {
                        flashSkipScreen.setVisibility(View.INVISIBLE);
                        topBarView.setVisibility(View.VISIBLE);
                    }
                };

                // checking conditionals
                if(!tiltReset && zTilt/2 == 0) tiltReset=true;

                if(score == maxIndex && !finished){
                    // scored all cards
                    finished = true;

                    topBarView.setVisibility(View.INVISIBLE);
                    gameWinScreen.setVisibility(View.VISIBLE);
                    gameWinScreen.setText("Finished!\nScore: " + score + "\nTime Left: " + millisUntilFinished/1000);
                    gameWinScreen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }

                // time not over and cards not finished - time to check for tilt

                if(!finished && zTilt > 5 && tiltReset) {
                    // face up after reset - skip
                    handler.postDelayed(flashSkip, 500);
                    tiltReset = false;
                    topBarView.setVisibility(View.INVISIBLE);
                    flashSkipScreen.setVisibility(View.VISIBLE);
                    v.vibrate(100);
                    changeQuestion(randomQuestion(category, true));
                }

                if(!finished && zTilt < -5 && tiltReset) {
                    // face down after reset - success
                    handler.postDelayed(flashSuccess, 500);
                    tiltReset = false;
                    topBarView.setVisibility(View.INVISIBLE);
                    flashSuccessScreen.setVisibility(View.VISIBLE);
                    v.vibrate(100);
                    changeQuestion(randomQuestion(category, false));
                }
            }

            @Override
            public void onFinish() {
                // time up
                finished = true;
                gameWinScreen.setVisibility(View.VISIBLE);
                topBarView.setVisibility(View.INVISIBLE);
                gameWinScreen.setText("Time's Up!\nScore: " + score);
                gameWinScreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }.start();
    }

    private String randomQuestion(int category, boolean skip) {
        Random random = new Random();
        int rand = random.nextInt(maxIndex);
        while(doneIndexes[rand]){
            rand = random.nextInt(maxIndex);
        }
        if(!skip) {
            increaseScore();
            doneIndexes[rand] = true;
        }
        return categories[category][rand];
    }

    private void changeQuestion(String text){
        Animation flipIn = AnimationUtils.loadAnimation(this, R.anim.flip_in);
        Animation flipOut = AnimationUtils.loadAnimation(this, R.anim.flip_out);
        //quizQuestionField.setAnimation(flipOut);
        quizQuestionTextView.setText(text);
        //quizQuestionField.startAnimation(flipIn);
    }

    private void increaseScore(){
        if(!finished) score++;
        quizScore.setText("Score: " + score);
    }
}
