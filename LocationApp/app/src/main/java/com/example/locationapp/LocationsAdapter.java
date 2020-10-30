package com.example.locationapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationViewHolder> {

    ArrayList<ResponseLocation> responseLocationArrayList;
    OnLocationItemClick onLocationItemClick;

    public LocationsAdapter(ArrayList<ResponseLocation> responseLocationArrayList,
                            OnLocationItemClick onLocationItemClick) {
        this.responseLocationArrayList = responseLocationArrayList;
        this.onLocationItemClick = onLocationItemClick;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.location_item, parent, false);
        return new LocationViewHolder(view, onLocationItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        ResponseLocation responseLocation = responseLocationArrayList.get(position);
        holder.nameTextView.setText(responseLocation.getName());
        holder.latitudeTextView.setText(responseLocation.getLatitude());
        holder.longitudeTextView.setText(responseLocation.getLongitude());
    }

    @Override
    public int getItemCount() {
        return responseLocationArrayList.size();
    }

    public void updateAdapter(ArrayList<ResponseLocation> responseLocations){
        responseLocationArrayList.clear();
        responseLocationArrayList.addAll(responseLocations);
        notifyDataSetChanged();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameTextView, latitudeTextView, longitudeTextView;
        OnLocationItemClick onLocationItemClick;

        public LocationViewHolder(@NonNull View itemView, OnLocationItemClick onLocationItemClick) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_textView);
            latitudeTextView = itemView.findViewById(R.id.latitude_textView);
            longitudeTextView = itemView.findViewById(R.id.longitude_textView);
            this.onLocationItemClick = onLocationItemClick;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onLocationItemClick.onItemClickListener(getAdapterPosition());
        }
    }

    public interface OnLocationItemClick{
        void onItemClickListener(int position);
    }

}
