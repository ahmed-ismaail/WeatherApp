package com.example.taskmoallem.models;

public class VideoModel {
    String videoTitle;
    String VideoPath;

    public VideoModel(String videoName, String videoPath) {
        this.videoTitle = videoName;
        this.VideoPath = videoPath;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoPath() {
        return VideoPath;
    }

    public void setVideoPath(String videoPath) {
        VideoPath = videoPath;
    }
}
