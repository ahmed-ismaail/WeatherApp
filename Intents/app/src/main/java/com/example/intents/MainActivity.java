package com.example.intents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PERMISSION_REQUEST_CODE = 100;
    EditText emailEditText, userNameEditText, passwordEditText, confirmPasswordEditText;
    ImageView profileImageView;
    Button loginButton;
    RadioGroup radioGroup;
    RadioButton male, female;
    Bitmap imageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.email_editText);
        userNameEditText = findViewById(R.id.userName_editText);
        passwordEditText = findViewById(R.id.password_editText);
        confirmPasswordEditText = findViewById(R.id.confirmPassword_editText);
        profileImageView = findViewById(R.id.profile_ImageView);
        loginButton = findViewById(R.id.login_button);
        radioGroup = findViewById(R.id.gender_RadioGroup);
        male = findViewById(R.id.male_radioButton);
        female = findViewById(R.id.female_radioButton);

        validateInputs();

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]{Manifest.permission.CAMERA,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_CODE);
                } else {
                    takeAPhoto();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(emailEditText.getText().toString()) ||
                        TextUtils.isEmpty(passwordEditText.getText().toString()) ||
                        TextUtils.isEmpty(confirmPasswordEditText.getText().toString())) {
                    Toast.makeText(MainActivity.this, "there is an empty field", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("imageUri", imageBitmap);
                    bundle.putString("email", emailEditText.getText().toString());
                    bundle.putString("userName", userNameEditText.getText().toString());
                    bundle.putString("password", passwordEditText.getText().toString());

                    if (male.isChecked()) {
                        bundle.putString("gender", "male");
                    } else {
                        bundle.putString("gender", "female");
                    }

                    Intent intent = new Intent(MainActivity.this, ProfilePage.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    public void takeAPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takeAPhoto();
            } else {
                Toast.makeText(this, "you don't have permission to access the camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = Objects.requireNonNull(data).getExtras();
            imageBitmap = (Bitmap) Objects.requireNonNull(extras).get("data");
            profileImageView.setImageBitmap(imageBitmap);
        }
    }

    public boolean isValid(String password) {
        int upper = 0;
        int lower = 0;
        if (password.length() < 4) {
            return false;
        } else {
            for (int i = 0; i < password.length(); i++) {
                if (Character.isUpperCase(password.charAt(i))) {
                    upper++;
                }
                if (Character.isLowerCase(password.charAt(i))) {
                    lower++;
                }
            }
            if (upper >= 2 && lower >= 2) return true;
        }
        return false;
    }

    public void validateInputs() {
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(emailEditText.getText().toString()) ||
                        !Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()) {
                    emailEditText.setError("not a valid email");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(emailEditText.getText().toString()) ||
                        !Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()) {
                    emailEditText.setError("not a valid email");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });

        passwordEditText.addTextChangedListener((customtext) s -> {
            if (!isValid(passwordEditText.getText().toString())) {
                passwordEditText.setError("password must contain at least 2 upperCases and 2 lowerCases ");
            }
        });

        confirmPasswordEditText.addTextChangedListener((customtext) s -> {
            if (!isValid(confirmPasswordEditText.getText().toString())) {
                confirmPasswordEditText.setError("password doesn't match");
            }
        });
    }
}