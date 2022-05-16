package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.example.teamder.logic.DataManager;
import com.example.teamder.models.InstanceBoundary;
import com.example.teamder.models.UserBoundary;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CreateProfileInterestsActivity extends AppCompatActivity {
    private MaterialButton createProfileInterests_BTN_finish;
    private Spinner createProfileDesc_SPIN_spinnerTags;

    DataManager dataManager;
    Bundle bundle;
    String userDesc;
    String tutorialsName;
    ArrayList<String> tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_create_profile_interests);
        dataManager=new DataManager ();

        findViews();

        getUserBoundaryDetails();

        tags=new ArrayList<> ();
        //TODO: function for this & put strings in R string
        //TODO: Add couple of tags and not only one(spinner by default adds only one), switch to another list
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Arts");
        arrayList.add("Electronics");
        arrayList.add("Programming");
        arrayList.add("Music");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,                         android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        createProfileDesc_SPIN_spinnerTags.setAdapter(arrayAdapter);
        createProfileDesc_SPIN_spinnerTags.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tutorialsName = parent.getItemAtPosition(position).toString();
                tags.add (tutorialsName);
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,          Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
        setListeners();
    }

    private void getInstanceBoundaryDesc() {
        bundle = getIntent().getExtras();
         userDesc = bundle.getString(getString(R.string.BUNDLE_INSTANCE_BOUNDARY_KEY));
    }

    private void getUserBoundaryDetails() {
        bundle = getIntent().getExtras();
        String json = bundle.getString(getString(R.string.BUNDLE_USER_BOUNDARY_KEY));
        UserBoundary userBoundary=new Gson ().fromJson(json, UserBoundary.class);

        dataManager.setUserBoundary (userBoundary);
        Log.d ("pttt", "userManager in create profile interests activity"+ dataManager.getUserBoundary ().getUserId ().getDomain ());
    }

    private void setListeners() {
        createProfileInterests_BTN_finish.setOnClickListener (view -> {
            startMainPageActivity();
        });
    }

    private void startMainPageActivity() {
        getInstanceBoundaryDesc();
        createInstanceBoundaryOfTypeUser();
        Intent intent=new Intent(this,MainPageActivity.class);
        startActivity (intent);
    }

    private void createInstanceBoundaryOfTypeUser() {
        //    public InstanceBoundary(String type, String name, CreatedBy createdBy, String description, List tags) {
        //TODO: move this to data mangaer
        String name = dataManager.getUserIdFromUserBoundary ();
        String userDomain=dataManager.getUserBoundary ().getUserId ().getDomain ();
        String userEmail=dataManager.getUserBoundary ().getUserId ().getEmail ();

        dataManager.setInstanceBoundary (new InstanceBoundary ("USER",name,userDomain,userEmail,userDesc,tags));

    }
    private void findViews() {
        createProfileInterests_BTN_finish=findViewById (R.id. createProfileInterests_BTN_finish);
        createProfileDesc_SPIN_spinnerTags = findViewById(R.id.createProfileDesc_SPIN_spinnerTags);

    }
}