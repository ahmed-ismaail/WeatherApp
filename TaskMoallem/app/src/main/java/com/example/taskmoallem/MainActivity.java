package com.example.taskmoallem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.taskmoallem.adapters.RecyclerViewAdapter;
import com.example.taskmoallem.adapters.VideoItemAdapter;
import com.example.taskmoallem.interfaces.ClickListener;
import com.example.taskmoallem.models.VideoModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClickListener {

    private ArrayList<String> SubjectNames = new ArrayList<>();
    private ArrayList<String> ImageUrls = new ArrayList<>();

    private static final int PERMISSION_CODE = 101;
    RecyclerView videoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSubjectSlider();

        videoRecyclerView = findViewById(R.id.Video_recyclerview);

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                readAllFIles();
            } else {
                requestPermission();
            }
        } else {
            readAllFIles();
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Allow Permission", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readAllFIles();
            }
        }
    }

    private void readAllFIles() {
        List<String> filePathsList = new ArrayList<>();
        String[] projection = {MediaStore.Video.VideoColumns.DATA};
        //get videos path from the external storage
        Cursor cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);

        //loop on the cursor to add the paths to filePathsList
        try {
            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    filePathsList.add(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                }
                while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<VideoModel> videoModelList = new ArrayList<>();

        for (String filePath : filePathsList) {
            File file = new File(filePath);
            videoModelList.add(new VideoModel(file.getName(), file.getAbsolutePath()));
        }

        videoRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        VideoItemAdapter videoItemAdapter = new VideoItemAdapter(this, videoModelList, MainActivity.this);
        videoRecyclerView.setAdapter(videoItemAdapter);

    }

    void intialSubjectRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.Subjects_recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(SubjectNames, ImageUrls, this);
        recyclerView.setAdapter(adapter);
    }

    //set subjects' icons
    void setSubjectSlider() {
        ImageUrls.add("https://i.ibb.co/88582yr/Physics.png");
        SubjectNames.add("Physics");
        ImageUrls.add("https://i.ibb.co/88582yr/Physics.png");
        SubjectNames.add("Biology");
        ImageUrls.add("https://i.ibb.co/88582yr/Physics.png");
        SubjectNames.add("History");
        ImageUrls.add("https://i.ibb.co/88582yr/Physics.png");
        SubjectNames.add("Algebra");
        ImageUrls.add("https://i.ibb.co/88582yr/Physics.png");
        SubjectNames.add("Chemistry");

        intialSubjectRecyclerView();
    }

    @Override
    public void onClickListener(String VideoPath) {
        startActivity(new Intent(MainActivity.this, VideoPlayerActivity.class).putExtra("path_file", VideoPath));
    }
}
