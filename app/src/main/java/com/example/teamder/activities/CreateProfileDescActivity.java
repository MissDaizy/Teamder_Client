package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.example.teamder.logic.DataManager;
import com.example.teamder.models.NewUserBoundary;
import com.example.teamder.models.UserBoundary;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

public class CreateProfileDescActivity extends AppCompatActivity {
    private EditText createProfileDesc_TF_userDescription;
    private MaterialButton createProfileDesc_BTN_next;

    private DataManager dataManager;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_create_profile_desc);
        dataManager =new DataManager ();

        findViews();
        getNewUserBoundaryDetails();
        setListeners();
    }

    private void getNewUserBoundaryDetails() {
        bundle = getIntent().getExtras();
        String json = bundle.getString(getString(R.string.BUNDLE_NEW_USER_BOUNDARY_KEY));
        dataManager.setNewUserBoundary (new Gson ().fromJson(json, NewUserBoundary.class));
    }

    private void setListeners() {
        createProfileDesc_BTN_next.setOnClickListener (view -> {
             startCreateProfileInterestsActivity();
        });

    }

    private void startCreateProfileInterestsActivity() {
        String json = new Gson ().toJson(dataManager.getNewUserBoundary ());
        String userDescription=createProfileDesc_TF_userDescription.getText ().toString ();
        Intent intent = new Intent (this,CreateProfileInterestsActivity.class);
        Bundle bundle =new Bundle ();
        bundle.putString(getString(R.string.BUNDLE_INSTANCE_BOUNDARY_KEY),userDescription);
        bundle.putString(getString(R.string.BUNDLE_NEW_USER_BOUNDARY_KEY),json);
        intent.putExtras(bundle);
        startActivity (intent);
    }

    private void findViews() {
        createProfileDesc_TF_userDescription=findViewById (R.id.createProfileDesc_TF_userDescription);
        createProfileDesc_BTN_next=findViewById (R.id.createProfileDesc_BTN_next);
    }
}