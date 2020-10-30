package com.example.contactsfragmentsapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsfragmentsapp.R;
import com.example.contactsfragmentsapp.data.Contacts;

import java.util.ArrayList;

public class ContactsMasterListFragment extends Fragment
        implements ContactsListAdapter.OnContactClickListener {

    OnListItemClick onListItemClick;

    ArrayList<Contacts> contactsArrayList;

    public interface OnListItemClick {
        void onItemClick(Contacts contacts);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            onListItemClick = (OnListItemClick) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnListItemClick");
        }
    }

    public ContactsMasterListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        initContactsArrayList();
        ContactsListAdapter contactsListAdapter = new ContactsListAdapter(contactsArrayList,
                this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(contactsListAdapter);

        return view;
    }

    private void initContactsArrayList() {
        contactsArrayList = new ArrayList<>();
        contactsArrayList.add(new Contacts("ahmed", "123", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("blknle", "5498", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("cdknklnle", "123498", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("ekndkled", "123265498", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("Ekldmwklem", "1232498", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("Ismail", "123265498", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("Som3a", "12398", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("Lolo", "498", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("Tony", "32658", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("Zizo", "128", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("Tefa", "123265498", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("l", "1298", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("m", "12324", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("n", "3498", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("o", "32654", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("p", "326549", "ahmed@gmail.com"));
        contactsArrayList.add(new Contacts("q", "28", "ahmed@gmail.com"));
    }

    @Override
    public void onClickListener(Contacts contacts) {
        onListItemClick.onItemClick(contacts);
    }
}
