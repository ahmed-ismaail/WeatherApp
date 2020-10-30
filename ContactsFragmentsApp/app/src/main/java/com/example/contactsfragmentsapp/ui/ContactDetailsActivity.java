package com.example.contactsfragmentsapp.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.contactsfragmentsapp.R;
import com.example.contactsfragmentsapp.data.Contacts;

public class ContactDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Contacts contacts = (Contacts) getIntent().getSerializableExtra("contact");

        FragmentManager fragmentManager = getSupportFragmentManager();
        ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment().getNewInstance(contacts);
        fragmentManager.beginTransaction()
                .add(R.id.detail_container, contactDetailsFragment)
                .commit();

    }
}
