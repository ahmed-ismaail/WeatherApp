package com.example.taskmoallem.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taskmoallem.R;
import com.example.taskmoallem.interfaces.ClickListener;
import com.example.taskmoallem.models.VideoModel;

import java.io.File;
import java.util.ArrayList;

public class VideoItemAdapter extends RecyclerView.Adapter<VideoItemAdapter.ViewHolder> {

    ArrayList<VideoModel> videoModelsList;
    Context context;
    ClickListener clickListener;

    public VideoItemAdapter(Context context, ArrayList<VideoModel> videoModelsList, ClickListener clickListener) {
        this.context = context;
        this.videoModelsList = videoModelsList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoModel videoModel = videoModelsList.get(position);

        MediaPlayer mediaPlayer = MediaPlayer.create(context, Uri.fromFile(new File(videoModel.getVideoPath())));
        double msec = 0;

        if (mediaPlayer != null) {
            msec = mediaPlayer.getDuration();
        }

        int hours   = (int) ((msec / (1000*60*60)) % 24);
        int minutes = (int) ((msec / (1000*60)) % 60);
        int seconds = (int) (msec / 1000) % 60 ;

        holder.durationTextView.setText(new StringBuilder().append(hours).append(":").append(minutes).append(":").append(seconds));

        Glide.with(context)
                .load(videoModel.getVideoPath())
                .into(holder.videoImage);

        double finalMsec = msec;
        holder.videoImage.setOnClickListener(v -> {
            if (finalMsec == 0) {
                Toast.makeText(context, "Invalid Video", Toast.LENGTH_SHORT).show();
            } else {
                clickListener.onClickListener(videoModel.getVideoPath());
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  durationTextView;
        ImageView videoImage, playCircleImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            durationTextView = itemView.findViewById(R.id.duration_textView);
            videoImage = itemView.findViewById(R.id.videoImage);
            playCircleImageView = itemView.findViewById(R.id.playCircle);
        }
    }
}
