package com.example.employeeapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    ListView employeesListView;
    ArrayAdapter employeesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_Text);

        button = findViewById(R.id.Search_btn);

        employeesListView = findViewById(R.id.list_view);

        employeesArrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1);


        final EmployeeDbHelper employee = new EmployeeDbHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("")) {

                    employeesArrayAdapter.clear();

                    Cursor cursor = employee.getEmployees(editText.getText().toString());

                    while (!cursor.isAfterLast()) {

                        employeesArrayAdapter.add(cursor.getString(0));
                        cursor.moveToNext();

                        employeesListView.setAdapter(employeesArrayAdapter);
                        employeesArrayAdapter.notifyDataSetChanged();

                    }
                } else
                    Toast.makeText(MainActivity.this, "EditText is empty, enter a name", Toast.LENGTH_SHORT).show();
            }
        });

        employeesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String employeeName = (String)employeesListView.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this,EmployeeDetails.class);

                intent.putExtra("name",employeeName);
                startActivity(intent);
            }
        });

    }
}
