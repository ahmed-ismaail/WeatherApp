package com.example.taskmoallem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taskmoallem.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> SubjectNames;
    private ArrayList<String> ImageUrls;
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> subjectNames, ArrayList<String> imageUrls, Context context) {
        SubjectNames = subjectNames;
        ImageUrls = imageUrls;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(ImageUrls.get(position))
                .into(holder.subjectImageView);

        holder.subjectNameTextView.setText(SubjectNames.get(position));
    }

    @Override
    public int getItemCount() {
        return SubjectNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView subjectImageView;
        TextView subjectNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectImageView = itemView.findViewById(R.id.imageView);
            subjectNameTextView = itemView.findViewById(R.id.Name_textView);
        }
    }
}
