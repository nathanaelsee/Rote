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
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(1);
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(2);
            }
        });
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(3);
            }
        });
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(4);
            }
        });
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(5);
            }
        });
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(6);
            }
        });
        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(7);
            }
        });
        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCategoryXActivity(8);
            }
        });
    }

    private void goToCategoryXActivity(int x) {
        if (x == 1) {
            Intent int1 = new Intent(this, CategoryOneActivity.class);
            startActivity(int1);
        }
        if (x == 2) {
            Intent int2 = new Intent(this, CategoryTwoActivity.class);
            startActivity(int2);
        }
        if (x == 3) {
            Intent int3 = new Intent(this, CategoryThreeActivity.class);
            startActivity(int3);
        }
        if (x == 4) {
            Intent int4 = new Intent(this, CategoryFourActivity.class);
            startActivity(int4);
        }
        if (x == 5) {
            Intent int5 = new Intent(this, CategoryFiveActivity.class);
            startActivity(int5);
        }
        if (x == 6) {
            Intent int6 = new Intent(this, CategorySixActivity.class);
            startActivity(int6);
        }
        if (x == 7) {
            Intent int7 = new Intent(this, CategorySevenActivity.class);
            startActivity(int7);
        }
        if (x == 8) {
            Intent int8 = new Intent(this, CategoryEightActivity.class);
            startActivity(int8);
        }

    }
}