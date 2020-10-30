package com.example.contactsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.contactsapp.R.id.address_textView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    ArrayList<Contact> contactsArrayList;

    public ContactAdapter(ArrayList<Contact> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contactsArrayList.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.emailTextView.setText(contact.getEmail());
        holder.addressTextView.setText(contact.getAddress());
        holder.genderTextView.setText(contact.getGender());
        holder.phoneTextView.setText(String.format("home: %s\noffice: %s\nmobile: %s",
                contact.getPhone().getHome(),
                contact.getPhone().getOffice(),
                contact.getPhone().getMobile()));
    }

    public void  updateDate(ArrayList<Contact> contacts){
        contactsArrayList.clear();
        contactsArrayList.addAll(contacts);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return contactsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView, addressTextView, genderTextView, phoneTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name_textView);
            emailTextView = itemView.findViewById(R.id.email_textView);
            addressTextView = itemView.findViewById(address_textView);
            genderTextView = itemView.findViewById(R.id.gender_textView);
            phoneTextView = itemView.findViewById(R.id.phone_textView);
        }
    }
}
