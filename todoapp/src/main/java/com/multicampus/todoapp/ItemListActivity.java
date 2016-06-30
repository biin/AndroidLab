package com.multicampus.todoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.multicampus.todoapp.vo.TodoItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ItemListActivity extends AppCompatActivity {

    public static final int MENU_MAKE_COMPLETE = 0;
    public static final int MENU_CANCEL_COMPLETE = 1;


    private ListView listView;
    private ArrayList<TodoItem> data;
    private int contextTargetPosition;

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

                TodoItem item = data.get(position);

                Intent intent = new Intent(ItemListActivity.this, ItemViewActivity.class);
                intent.putExtra("item", item);

                startActivity(intent);
            }
        });
    }

    private void init(){
//        makeStringTodoList();
//        makeXmlStringTodoList();
//        makeMapTodoList();
        makeTodoList();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

        contextTargetPosition = info.position;

        TodoItem todoItem = data.get(info.position);

        if(todoItem.isComplete()){
            menu.add(0, MENU_CANCEL_COMPLETE, 0, R.string.cancel_complete);
        }else{
            menu.add(0, MENU_MAKE_COMPLETE, 0, R.string.complete);
        }

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_item_list, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        TodoItem todoItem = data.get(contextTargetPosition);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.e("##################### ", "" + info.position);

        switch (item.getItemId()){
            case MENU_CANCEL_COMPLETE:
                completeItem(todoItem, false);
                break;
            case MENU_MAKE_COMPLETE:
                completeItem(todoItem, true);
                break;
            case R.id.deleteMenu:
                removeItem(todoItem);
                break;
        }

        refreshList();
        return true;
    }

    private void completeItem(TodoItem item, boolean complete){
        item.setComplete(complete);
        Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        // TODO DB 상태값 변경 작업 추가
    }

    private void removeItem(TodoItem item){
        data.remove(item);
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();

        // TODO DB에서 삭제하는 작업 추가
    }

    private void refreshList(){
        ((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
    }

    private void addItem(TodoItem item){
        int size = data.size();
        TodoItem lastItem = data.get(size-1);
        item.setTodoId(lastItem.getTodoId()+1);
        data.add(item);
        // TODO DB에 추가하는 작업 추가

        refreshList();
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

//                Toast.makeText(this, "Add Menu", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);

                final EditText todoTitle = new EditText(this);
                dialog.setView(todoTitle);

                dialog.setTitle(R.string.add_item_title)
                        .setMessage(R.string.add_item_msg)
                        .setIcon(android.R.drawable.ic_input_add)
                        .setPositiveButton(R.string.btn_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String title = todoTitle.getText().toString();

                                Intent intent = new Intent(ItemListActivity.this, ItemEditActivity.class);
                                intent.putExtra(Intent.EXTRA_TITLE, title);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });


                dialog.show();
                break;
            case R.id.exitMenu:
                Toast.makeText(this, "Exit Menu", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void makeStringTodoList(){
        ArrayList<String> data = getStringList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                data
        );
        listView.setAdapter(adapter);
    }

    private void makeXmlStringTodoList(){

//        ArrayList<String> data = getXmlStringList();
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.sample,
                android.R.layout.simple_list_item_1
        );
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

        data = getTodoList();
        TodoItemAdapter adapter = new TodoItemAdapter(
                this,
                data,
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
        TodoItem todo = new TodoItem(101, "Sample Todo", "This is map sample data. Delete and use.");
        list.add(todo);
        return list;
    }

}
