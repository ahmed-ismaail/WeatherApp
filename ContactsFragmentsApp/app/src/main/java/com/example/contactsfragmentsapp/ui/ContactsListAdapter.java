package com.example.contactsfragmentsapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsfragmentsapp.R;
import com.example.contactsfragmentsapp.data.Contacts;

import java.util.ArrayList;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactHolder> {

    ArrayList<Contacts> contactsArrayList;
    OnContactClickListener onContactClickListener;

    public ContactsListAdapter(ArrayList<Contacts> contactsArrayList,
                               OnContactClickListener onContactClickListener) {
        this.contactsArrayList = contactsArrayList;
        this.onContactClickListener = onContactClickListener;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_list_item, parent, false);
        return new ContactHolder(view,onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        holder.nameTextView.setText(contactsArrayList.get(position).getName());
        holder.phoneTextView.setText(contactsArrayList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return contactsArrayList.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameTextView, phoneTextView;
        OnContactClickListener onContactClickListener;

        public ContactHolder(@NonNull View itemView, OnContactClickListener onContactClickListener) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.contactName_textView);
            phoneTextView = itemView.findViewById(R.id.contactPhone_textView);
            this.onContactClickListener = onContactClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
              onContactClickListener.onClickListener(contactsArrayList.get(getAdapterPosition()));
        }
    }

    public interface OnContactClickListener {
        void onClickListener(Contacts contacts);
    }

}
