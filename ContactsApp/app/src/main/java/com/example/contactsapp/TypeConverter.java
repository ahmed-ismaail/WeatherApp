package com.example.contactsapp;

import com.google.gson.Gson;

public class TypeConverter {
    @androidx.room.TypeConverter
    public Phone toPhone(String s){
        return new Gson().fromJson(s,Phone.class);
    }

    @androidx.room.TypeConverter
    public String toString(Phone phone){
        return new Gson().toJson(phone);
    }
}
