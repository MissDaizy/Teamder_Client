package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.google.android.material.button.MaterialButton;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editProfile_TF_firstName;
    private EditText editProfile_TF_lastName;
    private EditText editProfile_TF_phoneNumber;
    private EditText editProfile_TF_Description;
    private MaterialButton editProfile_BTN_next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_edit_profile);

        findViews();
        setListeners();

    }

    private void setListeners() {
        editProfile_BTN_next.setOnClickListener(view -> {
            Intent intent = new Intent (this, MainPageActivity.class);
            startActivity (intent);
        });
    }

    private void findViews() {
        editProfile_TF_firstName = findViewById(R.id.editProfile_TF_firstName);
        editProfile_TF_lastName = findViewById(R.id.editProfile_TF_lastName);
        editProfile_TF_phoneNumber = findViewById(R.id.editProfile_TF_phoneNumber);
        editProfile_TF_Description = findViewById(R.id.editProfile_TF_Description);
        editProfile_BTN_next = findViewById(R.id.editProfile_BTN_next);
    }
}
