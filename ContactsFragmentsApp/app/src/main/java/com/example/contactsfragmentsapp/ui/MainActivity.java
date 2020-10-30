package com.example.contactsfragmentsapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactsfragmentsapp.R;
import com.example.contactsfragmentsapp.data.Contacts;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements ContactsMasterListFragment.OnListItemClick {

    private boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.detail_container) != null) {
            mTwoPane = true;

            ContactDetailsFragment detailsFragment = new ContactDetailsFragment()
                    .getNewInstance(new Contacts("Name", "Phone", "Email"));
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.detail_container, detailsFragment)
                    .commit();
        }
    }

    @Override
    public void onItemClick(Contacts contacts) {
        Toast.makeText(this, contacts.getName(),
                Toast.LENGTH_SHORT).show();

        if (mTwoPane) {
            ContactDetailsFragment detailsFragment = new ContactDetailsFragment()
                    .getNewInstance(contacts);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.detail_container, detailsFragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, ContactDetailsActivity.class);
            intent.putExtra("contact", contacts);
            startActivity(intent);
        }
    }
}