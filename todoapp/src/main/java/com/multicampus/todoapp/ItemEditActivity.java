package com.multicampus.todoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.multicampus.todoapp.vo.TodoItem;

public class ItemEditActivity extends AppCompatActivity {

    private Button btnSave;
    private Button btnCancel;
    private Button btnList;
    private EditText contentView;
    private Vibrator vibrator;
    private CheckBox importantView;

    private int todoId = 0;
    private TodoItem todoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LifeCycle", "1. onCreate");
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
        importantView = (CheckBox)findViewById(R.id.checkbox);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        Intent intent = getIntent();
        String title = intent.getStringExtra(Intent.EXTRA_TITLE);
        String content = intent.getStringExtra(Intent.EXTRA_TEXT);
        boolean isImportant = intent.getBooleanExtra("important", false);

        if(title == null || title.isEmpty()) {
            title = getResources().getString(R.string.notitle);
        }

        if(content == null){
            content = "";
        }

        importantView.setChecked(isImportant);

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
                addItem();
                finish();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemEditActivity.this, ItemListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }

    private TodoItem getTodoItem(){
        String title = getTitle().toString();
        String content = contentView.getText().toString();
        boolean important = importantView.isChecked();
        TodoItem item = new TodoItem(todoId, title, content, false, important);
        return item;
    }

    private void addItem(){
        TodoItem item = getTodoItem();
        DBHandler dbHandler = DBHandler.open(ItemEditActivity.this);
        dbHandler.insert(item);
        dbHandler.close();
    }

    @Override
    protected void onRestart() {
        Log.d("LifeCycle", "1.5 onRestart");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d("LifeCycle", "2. onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("LifeCycle", "3. onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("LifeCycle", "4. onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("LifeCycle", "5. onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("LifeCycle", "6. onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("LifeCycle", "4.5 onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("LifeCycle", "2.5 onRestoreInstanceState");
        //시스템에 의한 강제 종료
        super.onRestoreInstanceState(savedInstanceState);
    }
}
