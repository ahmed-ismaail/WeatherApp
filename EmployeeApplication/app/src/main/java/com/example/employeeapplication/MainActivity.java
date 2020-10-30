package com.example.employeeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button SearchButton;
    ListView employeesList;
    ArrayAdapter employeesAdapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        SearchButton = findViewById(R.id.Search_btn);

        arrayList = new ArrayList<>();
        employeesList = findViewById(R.id.listview);
        employeesAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1);


        final EmployeeDbHelper employee = new EmployeeDbHelper(this);

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")) {
                    employeesAdapter.clear();
                    Cursor cursor = employee.getEmpolyees(editText.getText().toString());
                    if (cursor.getCount() > 0) {
                        while (!cursor.isAfterLast()) {
                            employeesAdapter.add(cursor.getString(0));
                            cursor.moveToNext();
                        }
                        employeesList.setAdapter(employeesAdapter);
                        employeesAdapter.notifyDataSetChanged();
                    } else
                        Toast.makeText(MainActivity.this, "there is no employee match this name", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "you have to enter at least one letter", Toast.LENGTH_SHORT).show();
            }
        });

        employeesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String name = (String)employeesList.getItemAtPosition(position);

                        Intent intent = new Intent(MainActivity.this,EmployeeDetailsActivity.class);
                        intent.putExtra("name",name);

                        startActivity(intent);
            }
        });
    }
}
