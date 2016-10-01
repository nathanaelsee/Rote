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
<<<<<<< HEAD
        Button button2 = (Button) findViewById(R.id.menu_cat2);
=======
        Button button2 = (Button) findViewById(R.id.button2);
>>>>>>> refs/remotes/origin/master
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(2);
            }
        });
<<<<<<< HEAD
        Button button3 = (Button) findViewById(R.id.menu_cat3);
=======
        Button button3 = (Button) findViewById(R.id.button3);
>>>>>>> refs/remotes/origin/master
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(3);
            }
        });
<<<<<<< HEAD
        Button button4 = (Button) findViewById(R.id.menu_cat4);
=======
        Button button4 = (Button) findViewById(R.id.button4);
>>>>>>> refs/remotes/origin/master
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(4);
            }
        });
<<<<<<< HEAD
        Button button5 = (Button) findViewById(R.id.menu_cat5);
=======
        Button button5 = (Button) findViewById(R.id.button5);
>>>>>>> refs/remotes/origin/master
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(5);
            }
        });
<<<<<<< HEAD
        Button button6 = (Button) findViewById(R.id.menu_cat6);
=======
        Button button6 = (Button) findViewById(R.id.button6);
>>>>>>> refs/remotes/origin/master
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(6);
            }
        });
<<<<<<< HEAD
        Button button7 = (Button) findViewById(R.id.menu_cat7);
=======
        Button button7 = (Button) findViewById(R.id.button7);
>>>>>>> refs/remotes/origin/master
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(7);
            }
        });
<<<<<<< HEAD
        Button button8 = (Button) findViewById(R.id.menu_cat8);
=======
        Button button8 = (Button) findViewById(R.id.button8);
>>>>>>> refs/remotes/origin/master
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(8);
            }
        });
    }

    private void goToCategoryXActivity(int x) {
        Intent quizIntent = new Intent(this, CategoryEightActivity.class);
        quizIntent.putExtra("Category", x);
        startActivity(quizIntent);
    }
}