package com.example.cathy.sdapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button1 = (Button) findViewById(R.id.menu_cat1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(1);
            }
        });
        Button button2 = (Button) findViewById(R.id.menu_cat2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(2);
            }
        });
        Button button3 = (Button) findViewById(R.id.menu_cat3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(3);
            }
        });
        Button button4 = (Button) findViewById(R.id.menu_cat4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(4);
            }
        });
        Button button5 = (Button) findViewById(R.id.menu_cat5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(5);
            }
        });
        Button button6 = (Button) findViewById(R.id.menu_cat6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(6);
            }
        });
        Button button7 = (Button) findViewById(R.id.menu_cat7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(7);
            }
        });
        Button button8 = (Button) findViewById(R.id.menu_cat8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(8);
            }
        });
    }

    private void goToCategoryXActivity(int x) {
        Intent quizIntent = new Intent(this, QuizActivity.class);
        quizIntent.putExtra("CATEGORY", x);
        startActivity(quizIntent);
    }
}