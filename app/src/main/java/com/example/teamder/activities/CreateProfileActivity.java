package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.google.android.material.button.MaterialButton;

public class CreateProfileActivity extends AppCompatActivity {
    private EditText createProfile_TF_firstName;
    private EditText createProfile_TF_lastName;
    private EditText createProfile_TF_phoneNumber;
    private RadioButton createProfile_RadioBtn_female;
    private RadioButton createProfile_RadioBtn_male;
    private MaterialButton createProfile_BTN_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_create_profile);

        findViews();
        setListeners();
    }

    private void setListeners() {
        createProfile_BTN_next.setOnClickListener (view -> {
            Intent intent = new Intent (this,CreateProfileDescActivity.class);
            startActivity (intent);
        });

    }

    private void findViews() {
        createProfile_TF_firstName=findViewById (R.id.createProfile_TF_firstName);
        createProfile_TF_lastName=findViewById (R.id.createProfile_TF_lastName);
        createProfile_TF_phoneNumber=findViewById (R.id.createProfile_TF_phoneNumber);
//        createProfile_RadioBtn_female=findViewById (R.id.createProfile_RadioBtn_female);
//        createProfile_RadioBtn_male=findViewById (R.id.createProfile_RadioBtn_male);
        createProfile_BTN_next=findViewById (R.id.createProfile_BTN_next);
    }
}