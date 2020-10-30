package com.example.contactsapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class VolleyActivity extends AppCompatActivity {
    Button callApiButton, deleteAllButton, retrieveFromRoomButton;
    ArrayList<Contact> contactsArrayList;
    RecyclerView recyclerView;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callApiButton = findViewById(R.id.callApi_button);
        deleteAllButton = findViewById(R.id.deleteAll_button);
        retrieveFromRoomButton = findViewById(R.id.retrieveData_button);

        recyclerView = findViewById(R.id.recyclerView);
        contactsArrayList = new ArrayList<>();
        contactAdapter = new ContactAdapter(contactsArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(contactAdapter);

        callApiButton.setOnClickListener(view -> retrieveContactsFormApi());

        deleteAllButton.setOnClickListener(view -> deleteAllDatabase());

        retrieveFromRoomButton.setOnClickListener(view -> retrieveFromDatabase());
    }

    public void saveToDatabase() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();

                ContactsRoomDatabase db = Room.databaseBuilder(VolleyActivity.this,
                        ContactsRoomDatabase.class, "CONTACTS_DB").
                        fallbackToDestructiveMigration().build();

//                for (int i = 0; i < contactsArrayList.size(); i++) {
//                    Contact contact = new Contact();
//                    Phone phone = new Phone();
//                    contact.setName(contactsArrayList.get(i).getName());
//                    contact.setEmail(contactsArrayList.get(i).getEmail());
//                    contact.setAddress(contactsArrayList.get(i).getAddress());
//                    contact.setGender(contactsArrayList.get(i).getGender());
//                    phone.setHome(contactsArrayList.get(i).getPhone().getHome());
//                    phone.setMobile(contactsArrayList.get(i).getPhone().getMobile());
//                    phone.setOffice(contactsArrayList.get(i).getPhone().getOffice());
//                    contact.setPhone(phone);
                    db.contactsDAO().insertContact(contactsArrayList);
//                }
            }
        };
        thread.start();
    }

    public void retrieveContactsFormApi() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.androidhive.info/contacts/";
        JsonObjectRequest jsonRequest =
                new JsonObjectRequest(Request.Method.GET, url, null,
                        response -> {
                            Gson gson = new Gson();
                            try {
                                String contactsString = response.getJSONArray(
                                        "contacts").toString();

                               contactsArrayList = gson.fromJson(contactsString,
                                       new TypeToken<List<Contact>>(){}.getType());

                                contactAdapter.updateDate(contactsArrayList);
                                saveToDatabase();

                                Log.d("VOLLEY", contactsArrayList.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }, error -> Toast.makeText(this,
                        "Error while getting contacts " +
                                error.getMessage(), Toast.LENGTH_SHORT).show());
        queue.add(jsonRequest);
    }

    public void retrieveFromDatabase() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();

                List<Contact> contactsList = Room.databaseBuilder(VolleyActivity.this,
                        ContactsRoomDatabase.class, "CONTACTS_DB").
                        fallbackToDestructiveMigration().build().
                        contactsDAO().retrieveAllContacts();

                contactsArrayList.clear();
                contactsArrayList = (ArrayList<Contact>) contactsList;

                runOnUiThread(() -> contactAdapter.updateDate(contactsArrayList));
            }
        };
        thread.start();
    }

    public void deleteAllDatabase() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                Room.databaseBuilder(VolleyActivity.this,
                        ContactsRoomDatabase.class, "CONTACTS_DB").
                        fallbackToDestructiveMigration().build().contactsDAO().DeleteAllContacts();

                contactsArrayList.clear();
                runOnUiThread(() -> contactAdapter.updateDate(contactsArrayList));
            }
        };
        thread.start();
    }

}
