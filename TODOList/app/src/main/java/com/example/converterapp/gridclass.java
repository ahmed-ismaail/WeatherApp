package com.example.converterapp;



import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class gridclass extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridviewpage);

        final GridView gridView = findViewById(R.id.gridview);
        String myArr[] = getResources().getStringArray(R.array.grid);

        final ArrayList<String> mylist = new ArrayList<>(Arrays.asList(myArr));

        final ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,mylist);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(Character.isLowerCase(mylist.get(position).charAt(0))){
                    mylist.set(position,mylist.get(position).toUpperCase());
                }else if(Character.isUpperCase(mylist.get(position).charAt(0))){
                    mylist.set(position,mylist.get(position).toLowerCase());
                }

                adapter.notifyDataSetChanged();
            }
        });
    }
}
