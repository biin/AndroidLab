package com.multicampus.androidlab.ch07;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ch07_activity_custom_view);

        CircleView cv = new CircleView(this);
        setContentView(cv);

    }
}
