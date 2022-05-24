package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;

public class ViewProfileActivity extends AppCompatActivity {

    private ImageView ViewProfile_IMG_logo;
    private TextView ViewProfile_TXT_userEmail;
    private TextView ViewProfile_TXT_userName;
    private TextView ViewProfile_TXT_PhoneNumber;
    private TextView ViewProfile_TXT_ProfileDesc;
    private TextView ViewProfile_TXT_interestsTags;
    private Button ViewProfile_BTN_editBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.view_user_profile);

        findViews();
        setListeners();
    }

    private void setListeners() {
        ViewProfile_BTN_editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);

                //TODO change: do not finish this activity here, finish it in edit > when press APPLY btn
                finish();
            }
        });
    }

    private void findViews() {
        ViewProfile_BTN_editBtn = findViewById(R.id.ViewProfile_BTN_editBtn);
        ViewProfile_IMG_logo = findViewById(R.id.ViewProfile_IMG_logo);
        ViewProfile_TXT_userEmail = findViewById(R.id.ViewProfile_TXT_userEmail);
        ViewProfile_TXT_userName = findViewById(R.id.ViewProfile_TXT_userName);
        ViewProfile_TXT_PhoneNumber = findViewById(R.id.ViewProfile_TXT_PhoneNumber);
        ViewProfile_TXT_ProfileDesc= findViewById(R.id.ViewProfile_TXT_ProfileDesc);
        ViewProfile_TXT_interestsTags= findViewById(R.id.ViewProfile_TXT_interestsTags);
    }
}
