package com.example.teamder.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.example.teamder.logic.DataManager;
import com.example.teamder.models.InstanceOfTypeUser;
import com.example.teamder.models.UserBoundary;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

public class EditProfileActivity extends AppCompatActivity {
    Bundle bundle;

    private EditText editProfile_TF_Name;
    private EditText editProfile_TF_phoneNumber;
    private EditText editProfile_TF_Description;
    private MaterialButton editProfile_BTN_next;

    private DataManager dataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_edit_profile);
        dataManager = new DataManager ();

        findViews ();
        setListeners ();
        getUserBoundary ();
        getUserInstance ();
        initFields ();
    }



    private void initFields() {
        editProfile_TF_Name.setText (dataManager.getUsername ());
        editProfile_TF_Description.setText (dataManager.getUserDescription ());
        editProfile_TF_phoneNumber.setText (dataManager.getPhoneNumber ());
    }

    private void getUserInstance() {
        bundle = getIntent ().getExtras ();
        String instanceBoundaryJson = bundle.getString (getString (R.string.BUNDLE_USER_INSTANCE_BOUNDARY_KEY));

        dataManager.setInstanceOfTypeUser (new Gson ().fromJson (instanceBoundaryJson, InstanceOfTypeUser.class));
    }

    private void getUserBoundary() {
        bundle = getIntent ().getExtras ();
        String userBoundaryJson = bundle.getString (getString (R.string.BUNDLE_USER_BOUNDARY_KEY));

        dataManager.setUserBoundary (new Gson ().fromJson (userBoundaryJson, UserBoundary.class));
    }

    private void setListeners() {
        editProfile_BTN_next.setOnClickListener (view -> {
//            Intent intent = getIntent(getApplicationContext (), MainPageActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            String userBoundaryJson = new Gson ().toJson(dataManager.getUserBoundary ());
            String instanceBoundaryJson = new Gson ().toJson(dataManager.getInstanceOfTypeUser ());
            Intent intent = new Intent (this, MainPageActivity.class);
            Bundle bundle =new Bundle ();
            bundle.putString(getString(R.string.BUNDLE_USER_BOUNDARY_KEY),userBoundaryJson);
            bundle.putString(getString(R.string.BUNDLE_USER_INSTANCE_BOUNDARY_KEY),instanceBoundaryJson);
            intent.putExtras(bundle);
            startActivity (intent);
        });
    }
    private static Intent getIntent(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        return intent;
    }
    private void findViews() {
        editProfile_TF_Name = findViewById (R.id.editProfile_TF_Name);
        editProfile_TF_phoneNumber = findViewById (R.id.editProfile_TF_phoneNumber);
        editProfile_TF_Description = findViewById (R.id.editProfile_TF_Description);
        editProfile_BTN_next = findViewById (R.id.editProfile_BTN_next);
    }
}
