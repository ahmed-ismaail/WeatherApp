package com.example.contactsfragmentsapp.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.contactsfragmentsapp.R;
import com.example.contactsfragmentsapp.data.Contacts;

import java.io.Serializable;
import java.util.Objects;

public class ContactDetailsFragment extends Fragment {

    private Contacts contacts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contacts = (Contacts) Objects.requireNonNull(getArguments()).getSerializable("contact");
    }

    public ContactDetailsFragment getNewInstance(Contacts contacts) {
        ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("contact", (Serializable) contacts);
        contactDetailsFragment.setArguments(bundle);
        return contactDetailsFragment;
    }

    public ContactDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);

        TextView name = view.findViewById(R.id.name_textView);
        TextView phone = view.findViewById(R.id.phone_textView);
        TextView email = view.findViewById(R.id.email_textView);

        name.setText(contacts.getName());
        phone.setText(contacts.getPhone());
        email.setText(contacts.getEmail());

        return view;
    }
}
