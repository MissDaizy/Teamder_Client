package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.google.android.material.button.MaterialButton;

public class CreateProfileDescActivity extends AppCompatActivity {
    private MaterialButton createProfileDesc_BTN_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_create_profile_desc);

        findViews();
        setListeners();
    }

    private void setListeners() {
        createProfileDesc_BTN_next.setOnClickListener (view -> {
            Intent intent = new Intent (this,CreateProfileInterestsActivity.class);
            startActivity (intent);
        });

    }

    private void findViews() {
        createProfileDesc_BTN_next=findViewById (R.id.createProfileDesc_BTN_next);
    }
}