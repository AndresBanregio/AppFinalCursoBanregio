package com.example.activity5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Entidad> listItems;
    public Adaptador(Context context, ArrayList<Entidad> listtems){
        this.context=context;
        this.listItems=listtems;
    }
    public int getCount(){
        return listItems.size();
    }
    public Object getItem(int position){
        return listItems.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Entidad item =(Entidad) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.lit_item_users,null);
        TextView nombre= (TextView) view.findViewById(R.id.textName);
        Button btnEdit = (Button) view.findViewById(R.id.buttonItemEdit);
        btnEdit.setTag(i);
        System.out.println("**********//////////"+(int)btnEdit.getTag());
        nombre.setText(item.getName());
        return view;
    }
}
