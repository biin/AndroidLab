package com.multicampus.todoapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ItemListActivity extends AppCompatActivity {

    public static final int MENU_MAKE_COMPLETE = 0;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        setView();
        setEvent();
        init();
    }

    private void setView(){
        listView = (ListView) findViewById(R.id.listView);
    }
    private void setEvent(){

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(ItemListActivity.this.getClass().getSimpleName(), position + ", " + id + " click");
            }
        });
    }

    private void init(){
//        makeStringTodoList();
//        makeXmlStringTodoList();
        makeMapTodoList();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, MENU_MAKE_COMPLETE, 0, R.string.complete);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_item_list, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        switch (item.getItemId()){
            case MENU_MAKE_COMPLETE:
                Toast.makeText(this, "Complete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.deleteMenu:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
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
        inflater.inflate(R.menu.item_list, menu);


        return true; // true : 만들어서 바로 보여줌, false: 그냥 만들기만 하고 보여주지 않음
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.addMenu:
                Toast.makeText(this, "Add Menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exitMenu:
                Toast.makeText(this, "Exit Menu", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void makeStringTodoList(){
        ArrayList<String> data = getStringList();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }

    private void makeXmlStringTodoList(){

//        ArrayList<String> data = getXmlStringList();
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.sample, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
    }

    private void makeMapTodoList(){

//        SimpleAdapter adapter = new SimpleAdapter(
//                this,
//                getMapList(),
//                android.R.layout.simple_list_item_2,
//                new String[]{"title","content"},
//                new int[]{android.R.id.text1, android.R.id.text2}
//        );

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                getMapList(),
                R.layout.list_todo_item,
                new String[]{"title","content"},
                new int[]{R.id.titleTxt, R.id.contentTxt}
        );
        listView.setAdapter(adapter);
    }
    private void makeTodoList(){

        TodoItemAdapter adapter = new TodoItemAdapter(
                this,
                getTodoList(),
                R.layout.list_todo_item
        );

        listView.setAdapter(adapter);
    }

    /*
    * ArrayList로 만든 리스트 데이터 반환
    * */
    private ArrayList<String> getStringList(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("Sample 1");
        list.add("Sample 2");
        list.add("Sample 3");
        list.add("Sample 4");
        list.add("Sample 5");

        return list;
    }
    /*
    * ArrayList로 만든 리스트 데이터 반환(XML으로 부터)
    * */
    private ArrayList<String> getXmlStringList(){
        Resources resources = getResources();
        String[] array = resources.getStringArray(R.array.sample);
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(array));

        return list;
    }

    private ArrayList<Map<String, String>> getMapList(){
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();

        Map<String, String> item = new HashMap<String, String>();
        item.put("title", "Sample Map 1");
        item.put("content", "This is map sample data. Delete and use.");
        list.add(item);

        item = new HashMap<String, String>();
        item.put("title", "이번 달에 할 일");
        item.put("content", "메일 보내기 aaa@bbb.com 전화 010-111-2222 쇼핑 http://www.gmarket.co.kr");
        list.add(item);

        item = new HashMap<String, String>();
        item.put("title", "이번 달에 할 일");
        item.put("content", "메일 보내기 aaa@bbb.com 전화 010-111-2222 쇼핑 http://www.gmarket.co.kr");
        list.add(item);
        return list;
    }

    private ArrayList<TodoItem> getTodoList(){
        ArrayList<TodoItem> list = new ArrayList<TodoItem>();
        TodoItem todo = new TodoItem(101, "Sample Map 1", "This is map sample data. Delete and use.");
        list.add(todo);
        todo = new TodoItem(102, "이번 달에 할 일", "메일 보내기 aaa@bbb.com 전화 010-111-2222 쇼핑 http://www.gmarket.co.kr");
        list.add(todo);
        return list;
    }

}
