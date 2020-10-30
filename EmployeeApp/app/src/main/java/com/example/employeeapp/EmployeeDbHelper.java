package com.example.employeeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmployeeDbHelper extends SQLiteOpenHelper {

    private static final String DATABASENAME = "employee.db";

    public EmployeeDbHelper(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table employee(EmpID integer primary key autoincrement," +
                "Name text not null, Title text not null,Phone text not null," +
                "Email text not null, Dept_id integer, FOREIGN KEY(Dept_id) REFERENCES Department (DeptID));");

        db.execSQL("create table Department(DeptID integer primary key autoincrement," +
                "name text);");

        ContentValues valuess = new ContentValues();
        valuess.put("name", "sales");
        db.insert("Department",null,valuess);

        valuess.put("name", "marketing");
        db.insert("Department",null,valuess);

        ContentValues values = new ContentValues();
        values.put("Name","ahmed");
        values.put("Title","developer");
        values.put("Phone","3246946");
        values.put("Email","ahmed@gmail.com");
        values.put("Dept_id", 2);
        db.insert("employee",null,values);

        values.put("Name","abdelaziz");
        values.put("Title","developer");
        values.put("Phone","3246946");
        values.put("Email","ahmed@gmail.com");
        values.put("Dept_id", 1);
        db.insert("employee",null,values);

        values.put("Name","ahmed abdelaziz");
        values.put("Title","developer");
        values.put("Phone","3246946");
        values.put("Email","ahmed@gmail.com");
        values.put("Dept_id", 1);
        db.insert("employee",null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
             db.execSQL("drop table if exists employee");
             db.execSQL("drop table if exists Department");

             onCreate(db);
    }

    public Cursor getEmployees(String Name){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery
                ("Select Name from employee where Name like '%"+Name+"%'",null);

        cursor.moveToFirst();
        db.close();

        return cursor;
    }

    public Cursor getEmployeesData(int empId){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery
                ("Select * from employee where EmpID ='" + empId + "'", null);

        cursor.moveToFirst();
        db.close();

        return cursor;
    }

    public Cursor getEmpolyeeID(String name) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery
                ("Select EmpID from employee where Name like '%" + name + "%'", null);

        cursor.moveToFirst();
        db.close();

        return cursor;
    }

    public Cursor getDepartmentName(int DeptID){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery
                ("Select name from Department where DeptID ='" + DeptID + "'", null);

        cursor.moveToFirst();
        db.close();

        return cursor;
    }

}
