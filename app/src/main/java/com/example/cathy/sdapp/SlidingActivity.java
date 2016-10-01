package com.example.cathy.sdapp;

/**
 * Created by joshuazeitsoff on 10/1/16.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class SlidingActivity extends Fragment {

    public static SlidingActivity newInstance(){
        SlidingActivity f = new SlidingActivity();
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.sliding, container, false);

        return rootView;
    }
}




