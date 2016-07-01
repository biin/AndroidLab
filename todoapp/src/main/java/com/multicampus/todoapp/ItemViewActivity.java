package com.multicampus.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.multicampus.todoapp.vo.TodoItem;

public class ItemViewActivity extends AppCompatActivity {

    private TextView content;
    private Button btnEdit;
    private Button btnList;
    private TodoItem todoItem;

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

        Intent intent = getIntent();
        todoItem = (TodoItem) intent.getSerializableExtra("item");

        setTitle(todoItem.getTitle());
        content.setText(todoItem.getContent());
    }

    private void setEvent(){

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ItemViewActivity.this, "Toast Edit Message", Toast.LENGTH_SHORT).show();
                Log.d("TodoApp", "btnEdit Clicked!!!");

                Intent intent = new Intent(ItemViewActivity.this, ItemEditActivity.class);
                intent.putExtra(Intent.EXTRA_TITLE, todoItem.getTitle());
                intent.putExtra(Intent.EXTRA_TEXT, todoItem.getContent());
                startActivity(intent);

            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ItemViewActivity.this, "Toast List Message", Toast.LENGTH_LONG).show();
                Log.e(ItemViewActivity.class.getName(), "btnList Clicked!!!");
                Intent intent = new Intent(ItemViewActivity.this, ItemListActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // configure menu

        // by Coding
        //menu.add(0, 0, 0, R.string.share);
        //menu.add(0, 1, 0, R.string.delete);

        //by xml : inflater 사용
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_view, menu);


        return true; // true : 만들어서 바로 보여줌, false: 그냥 만들기만 하고 보여주지 않음
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.share:
//                    Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
//                intent.setType("application/zip");
//                startActivity(intent);

                intent.putExtra(Intent.EXTRA_TITLE, todoItem.getTitle());
                intent.putExtra(Intent.EXTRA_TEXT, todoItem.getContent());
                intent.putExtra("important", todoItem.isImportant());

                startActivity(Intent.createChooser(intent, "Select a application to share." ));
                break;
            case R.id.delete:

                DBHandler dbHandler = DBHandler.open(this);
                dbHandler.delete(todoItem.getTodoId());
                dbHandler.close();

                Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
