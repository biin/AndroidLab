package com.multicampus.androidlab.ch05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.multicampus.androidlab.R;

public class FrameLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch05_activity_frame_layout);

        Button pushButton = (Button)findViewById(R.id.btn);
        final ImageView img = (ImageView)findViewById(R.id.korando);

        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(img.getVisibility() == View.VISIBLE){
                    img.setVisibility(View.GONE);
                }else{
                    img.setVisibility(View.VISIBLE);
                }
            }
        });


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
            }
        });
    }
}
