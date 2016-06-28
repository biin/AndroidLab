package com.multicampus.todoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ItemViewActivity extends AppCompatActivity {

    private TextView content;
    private Button btnEdit;
    private Button btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        setView();
        setEvent();

    }

    private void setView(){
        content = (TextView)findViewById(R.id.content);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnList = (Button)findViewById(R.id.btnList);
    }

    private void setEvent(){

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ItemViewActivity.this, "Toast Edit Message", Toast.LENGTH_SHORT).show();
                Log.d("TodoApp", "btnEdit Clicked!!!");


            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ItemViewActivity.this, "Toast List Message", Toast.LENGTH_LONG).show();
                Log.e(ItemViewActivity.class.getName(), "btnList Clicked!!!");
            }
        });

    }
}
