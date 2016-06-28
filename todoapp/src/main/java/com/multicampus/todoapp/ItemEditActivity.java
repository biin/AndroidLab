package com.multicampus.todoapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ItemEditActivity extends AppCompatActivity {

    private Button btnSave;
    private Button btnCancel;
    private Button btnList;
    private EditText content;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);

        setView();
        setEvent();
    }

    private void setView(){
        btnSave = (Button)findViewById(R.id.btnSave);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnList = (Button)findViewById(R.id.btnList);
        content = (EditText) findViewById(R.id.content);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    }

    private void setEvent(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(1000);
            }
        });
    }
}
