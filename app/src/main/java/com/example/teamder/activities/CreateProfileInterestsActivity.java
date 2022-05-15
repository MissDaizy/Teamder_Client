package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.google.android.material.button.MaterialButton;

public class CreateProfileInterestsActivity extends AppCompatActivity {
    private MaterialButton createProfileInterests_BTN_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_create_profile_interests);

        findViews();
        setListeners();
    }

    private void setListeners() {
        createProfileInterests_BTN_finish.setOnClickListener (view -> {
            Intent intent=new Intent(this,MainPageActivity.class);
            startActivity (intent);
        });
    }

    private void findViews() {
        createProfileInterests_BTN_finish=findViewById (R.id. createProfileInterests_BTN_finish);
    }
}