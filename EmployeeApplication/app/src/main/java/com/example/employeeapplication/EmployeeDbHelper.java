package com.example.employeeapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.wifi.hotspot2.pps.Credential;

import java.util.Random;

public class EmployeeDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "employee.db";

    public EmployeeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Empolyee(EmpID integer primary key autoincrement, " +
                "Name text not null, Title text not null, Phone text not null, " +
                "Email text not null, Dept_id integer, FOREIGN KEY(Dept_id) REFERENCES Department (DeptID));");

        db.execSQL("create table Department(DeptID integer primary key autoincrement, " +
                "name text);");

        ContentValues values2 = new ContentValues();
        values2.put("name", "software");
        db.insert("Department",null,values2);

        values2.put("name", "bio");
        db.insert("Department",null,values2);

        ContentValues values = new ContentValues();
        values.put("Name", "ahmed");
        values.put("Title", "engineer");
        values.put("Phone", "123456789");
        values.put("Email", "ahmed@gmail.com");
        values.put("Dept_id", 1);
        db.insert("Empolyee", null, values);

        values.put("Name", "ahmed mohamed");
        values.put("Title", "engineer");
        values.put("Phone", "123456789");
        values.put("Email", "ahmed@gmail.com");
        values.put("Dept_id", 2);
        db.insert("Empolyee", null, values);

        values.put("Name", "ahme");
        values.put("Title", "engineer");
        values.put("Phone", "123456789");
        values.put("Email", "ahmed@gmail.com");
        values.put("Dept_id", 1);
        db.insert("Empolyee", null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Empolyee");
        db.execSQL("drop table if exists Department");

        onCreate(db);
    }

    public Cursor getEmpolyees(String name) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery
                ("Select Name from Empolyee where Name like '%" + name + "%'", null);

        cursor.moveToFirst();
        db.close();

        return cursor;
    }

    public Cursor getEmployeesData(int empId){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery
                ("Select * from Empolyee where EmpID ='" + empId + "'", null);

        cursor.moveToFirst();
        db.close();

        return cursor;
    }

    public Cursor getEmpolyeeID(String name) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery
                ("Select EmpID from Empolyee where Name like '%" + name + "%'", null);

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
