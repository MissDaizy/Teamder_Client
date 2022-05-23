package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.example.teamder.logic.DataManager;
import com.example.teamder.models.InstanceOfTypeUser;
import com.example.teamder.models.UserBoundary;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

public class CreateGroupActivity extends AppCompatActivity {
    private TextInputEditText createTeamGroup_TXT_setProjectName;
    private TextInputEditText createTeamGroup_TXT_setProjectDescription;
    private MaterialButton createTeamGroup_BTN_next;

    private Bundle bundle;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_create_group);

        dataManager=new DataManager ();
        bundle=new Bundle ();

        getUserBoundary();

        findViews();
        getStrings();
        setListener();
    }

    private void setListener() {
        createTeamGroup_BTN_next.setOnClickListener(view -> {
            putInBundle();
            startCreateTeamGroupNextActivity();
        });
    }

    private void startCreateTeamGroupNextActivity() {
        Intent intent = new Intent(CreateGroupActivity.this, CreateGroupNextActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void putInBundle() {
        String groupName = createTeamGroup_TXT_setProjectName.getText ().toString ();
        String groupDescrpition = createTeamGroup_TXT_setProjectDescription.getText ().toString ();
        bundle.putString(getString(R.string.BUNDLE_GROUP_NAME_KEY),groupName);
        bundle.putString(getString(R.string.BUNDLE_GROUP_DESCRIPTION_KEY),groupDescrpition);
    }

    private void getStrings() {
        String groupName = createTeamGroup_TXT_setProjectName.getText ().toString ();
        String groupDescrpition = createTeamGroup_TXT_setProjectDescription.getText ().toString ();

    }

    private void findViews() {
        createTeamGroup_TXT_setProjectName = findViewById(R.id.createGroup_TXT_setProjectName);
        createTeamGroup_TXT_setProjectDescription = findViewById(R.id.createGroup_TXT_setProjectDescription);
        createTeamGroup_BTN_next = findViewById(R.id.createGroup_BTN_next);
    }

    private void getUserBoundary() {
        bundle = getIntent ().getExtras ();
        String userBoundaryJson = bundle.getString (getString (R.string.BUNDLE_USER_BOUNDARY_KEY));
        String instanceBoundaryJson = bundle.getString (getString (R.string.BUNDLE_USER_INSTANCE_BOUNDARY_KEY));

        dataManager.setUserBoundary ((new Gson ().fromJson (userBoundaryJson, UserBoundary.class)));
        dataManager.setInstanceOfTypeUser (new Gson ().fromJson (instanceBoundaryJson, InstanceOfTypeUser.class));
        Log.d ("pttt", "description in main page :"+dataManager.getUserDescription ());
    }




}
