package com.multicampus.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.multicampus.todoapp.view.TitleTextView;
import com.multicampus.todoapp.vo.TodoItem;

import java.util.ArrayList;

/**
 * Created by student on 2016-06-29.
 */
public class TodoItemAdapter extends BaseAdapter{

    private ArrayList<TodoItem> data;
    private Context context;
    private int layoutId;
    private LayoutInflater layoutInflater;

    public TodoItemAdapter(Context context, ArrayList<TodoItem> data, int layoutId) {
        this.data = data;
        this.context = context;
        this.layoutId = layoutId;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
}

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public TodoItem getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getTodoId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // position에 해당하는 데이터를 통해 convertView를 생성하여 반환.
        // parent : listview임
        if(convertView == null){
            convertView = layoutInflater.inflate(layoutId, parent, false);
        }

        TodoItem todoItem = getItem(position);

        TitleTextView titleView = (TitleTextView)convertView.findViewById(R.id.titleTxt);
        TextView contentView = (TextView)convertView.findViewById(R.id.contentTxt);
        titleView.setText(todoItem.getTitle());
        contentView.setText(todoItem.getContent());

        titleView.setComplete(todoItem.isComplete());
        return convertView;
    }
}
