package com.multicampus.todoapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by student on 2016-06-29.
 */
public class TodoItemAdapter extends BaseAdapter{

    private ArrayList<TodoItem> data;
    private Context context;
    private int layoutId;


    public TodoItemAdapter(Context context, ArrayList<TodoItem> data, int layoutId) {
        this.data = data;
        this.context = context;
        this.layoutId = layoutId;
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
        return null;
    }
}
