package com.multicampus.todoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ItemEditActivity extends AppCompatActivity {

    private Button btnSave;
    private Button btnCancel;
    private Button btnList;
    private EditText contentView;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);

        setView();
//        setCustomActionBar();
        setEvent();
    }

    private void setCustomActionBar(){

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.edit_actionbar, null);
        EditText mTitleTextView = (EditText) mCustomView.findViewById(R.id.edit_title);
        mTitleTextView.setText("My Own Title");
        mActionBar.setCustomView(mCustomView);
    }

    private void setView(){
        btnSave = (Button)findViewById(R.id.btnSave);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnList = (Button)findViewById(R.id.btnList);
        contentView = (EditText) findViewById(R.id.content);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        Intent intent = getIntent();
        String title = intent.getStringExtra(Intent.EXTRA_TITLE);
        String content = intent.getStringExtra(Intent.EXTRA_TEXT);

        if(title == null || title.isEmpty()) {
            title = "No title";
        }

        if(content == null){
            content = "";
        }

        setTitle(title);
        setContent(content);
    }

    private void setContent(String content){
        contentView.setText(content);
    }

    private void setEvent(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(1000);
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemEditActivity.this, ItemListActivity.class);
                startActivity(intent);
            }
        });
    }
}
