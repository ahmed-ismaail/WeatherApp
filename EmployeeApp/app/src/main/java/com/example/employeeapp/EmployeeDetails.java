package com.example.employeeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class EmployeeDetails extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        textView = findViewById(R.id.textView);

        final EmployeeDbHelper employee = new EmployeeDbHelper(this);

        Cursor EmpolyeeID = employee.getEmpolyeeID(getIntent().getStringExtra("name"));

        Cursor EmployeesData = employee.getEmployeesData(EmpolyeeID.getInt(0));

        Cursor cursorDepartment = employee.getDepartmentName(EmployeesData.getInt(5));

        textView.setText("Name: " + EmployeesData.getString(1) + "\n" + "Title: " + EmployeesData.getString(2) + "\n"
                + "phone: " + EmployeesData.getString(3) + "\n" + "email: " + EmployeesData.getString(4) + "\n"
                + "Depatment: " + cursorDepartment.getString(0));

    }

}
