package com.example.employeeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class EmployeeDetailsActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        textView = findViewById(R.id.textView2);

        final EmployeeDbHelper employee = new EmployeeDbHelper(this);

        Cursor cursorID = employee.getEmpolyeeID(getIntent().getStringExtra("name"));
        Cursor cursor = employee.getEmployeesData(cursorID.getInt(0));

        Cursor cursorDepartment = employee.getDepartmentName(cursor.getInt(5));

        textView.setText("Name: " + cursor.getString(1)+"\n"
                +"Title: " + cursor.getString(2)+"\n"
                +"phone: " + cursor.getString(3)+"\n"
                +"email: " + cursor.getString(4)+"\n"
                +"Depatment: "+ cursorDepartment.getString(0));
    }
}
