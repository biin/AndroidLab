package com.multicampus.androidlab.ch07;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.multicampus.androidlab.R;

public class CustomViewActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



//        CircleView cv = new CircleView(this);
//        setContentView(cv);

        setContentView(R.layout.ch07_activity_custom_view);

        CircleView cv = (CircleView)findViewById(R.id.circle);

        CircleClickListener circleClickListener = new CircleClickListener();

        //2. Class implements Listener
        cv.setOnClickListener(circleClickListener);
        //3. Activity implements Listener : Activity 목적에서 벗어나기에 적합하지 않음
        cv.setOnClickListener(this);
        //4. View implements Listener
        cv.setOnClickListener(cv);

        View.OnClickListener listener2 = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d(CustomViewActivity.this.getClass().getSimpleName(), "5. 익명 inner class사용");
            }
        };

        cv.setOnClickListener(listener2);


        cv.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d(CustomViewActivity.this.getClass().getSimpleName(), "6. 익명 inner class의 임시 객체 사용");
            }
        });

    }

    @Override
    public void onClick(View v) {
        Log.d(CustomViewActivity.this.getClass().getSimpleName(), "3. Activity implements Listener");
    }

    class CircleClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.d(CustomViewActivity.this.getClass().getSimpleName(), "2. Class implements Listener");
        }
    }
}
