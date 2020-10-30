package com.example.contactsapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Contact.class}, version = 10)
@TypeConverters(TypeConverter.class)
public abstract class ContactsRoomDatabase extends RoomDatabase {
    public abstract ContactsDAO contactsDAO();
}
