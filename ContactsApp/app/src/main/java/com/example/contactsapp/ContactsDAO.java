package com.example.contactsapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
interface ContactsDAO {
    @Query("SELECT * FROM contacts")
    List<Contact> retrieveAllContacts();

    @Insert
    void insertContact(ArrayList<Contact> contacts);

//    @Insert
//    void insertPhone(Phone... phones);

    @Query("DELETE FROM contacts")
    void DeleteAllContacts();
}
