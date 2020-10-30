package com.example.intents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ProfilePage extends AppCompatActivity {

    TextView emailTextView, userNameTextView, passwordTextView, genderTextView;
    ImageView homeProfileImageView;
    Button dialButton, sendEmailButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_profile);

        emailTextView = findViewById(R.id.emailValue_textView);
        userNameTextView = findViewById(R.id.userNameValue_textView);
        passwordTextView = findViewById(R.id.passwordValue_textView);
        genderTextView = findViewById(R.id.genderValue_textView);
        homeProfileImageView = findViewById(R.id.profile_ImageView);
        dialButton = findViewById(R.id.dial_button);
        sendEmailButton = findViewById(R.id.sendEmail_button);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            emailTextView.setText(bundle.getString("email"));
            userNameTextView.setText(bundle.getString("userName"));
            passwordTextView.setText(bundle.getString("password"));
            genderTextView.setText(bundle.getString("gender"));
        }
        loadImage();

        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:" + 123456789);
                intent.setData(uri);
                startActivity(Intent.createChooser(intent,"choose"));
            }
        });

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                Uri uri = Uri.parse("mailto:");
                intent.setData(uri);
                Intent intentChooser = Intent.createChooser(intent,"choose app: ");
                startActivity(intentChooser);
            }
        });


    }

    public void loadImage() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getParcelable("imageUri") != null) {
                Bitmap bitmap = bundle.getParcelable("imageUri");
                homeProfileImageView.setImageBitmap(bitmap);
            }
        }
    }
}
