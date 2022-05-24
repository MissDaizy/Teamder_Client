package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.example.teamder.logic.DataManager;
import com.example.teamder.models.InstanceOfTypeUser;
import com.example.teamder.models.RoleType;
import com.example.teamder.models.UserBoundary;
import com.example.teamder.retrofit.RetrofitService;
import com.example.teamder.service.JsonApiInstances;
import com.example.teamder.service.JsonApiUsers;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private Bundle bundle;

    private EditText editProfile_TF_Name;
    private EditText editProfile_TF_phoneNumber;
    private EditText editProfile_TF_Description;
    private MaterialButton editProfile_BTN_applyChanges;

    private DataManager dataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_edit_profile);
        dataManager = new DataManager ();

        findViews ();
//TODO: back
        getUserBoundary ();
        getUserInstance ();
        initFields ();
        setListeners ();

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
        editProfile_BTN_applyChanges.setOnClickListener (view -> {
            updateUserRoleType ();

        });

    }

    private void updateUserRoleType() {
        String userDomain = dataManager.getUserDomain ();
        String userEmail = dataManager.getUserEmail ();

        dataManager.updateUserRoleTypeData ();

        startSplashActivity ();

        RetrofitService retrofitService = new RetrofitService ();

        JsonApiUsers jsonApiUsers = retrofitService.getRetrofit ().create (JsonApiUsers.class);

        Call<Void> call = jsonApiUsers.updateUser (userDomain, userEmail, dataManager.getUserBoundary ());
        call.enqueue (new Callback<Void> () {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful ()) {
                    Log.d ("pttt", "" + response.code ());
                }
                if (dataManager.getUserBoundaryRoleType ().equals (RoleType.MANAGER.toString ())) {
                    Log.d ("pttt", "Success!!! user role updated to manager");
                    updateUser ();
                } else {
                    startMainPageActivity ();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d ("pttt", "Failure!!!, Message: " + t.getMessage ());
            }
        });
    }

    private void updateUser() {
        String usernameField = editProfile_TF_Name.getText ().toString ();
        String userDomain = dataManager.getUserDomain ();
        String userEmail = dataManager.getUserEmail ();

        dataManager.updateUsername (usernameField);

        RetrofitService retrofitService = new RetrofitService ();

        JsonApiUsers jsonApiUsers = retrofitService.getRetrofit ().create (JsonApiUsers.class);

        Call<Void> call = jsonApiUsers.updateUser (userDomain, userEmail, dataManager.getUserBoundary ());
        call.enqueue (new Callback<Void> () {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful ()) {
                    Log.d ("pttt", "" + response.code ());
                }
                Log.d ("pttt", "Success!!! user role updated to player");
                updateUserInstance ();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d ("pttt", "Failure!!!, Message: " + t.getMessage ());
            }
        });
    }

    private void updateUserInstance() {
        String phoneNumField = editProfile_TF_phoneNumber.getText ().toString ();
        String descriptionField = editProfile_TF_Description.getText ().toString ();

        String instanceDomain = dataManager.getInstanceOfTypeUser ().getInstanceId ().getDomain ();
        String instanceId = dataManager.getInstanceOfTypeUser ().getInstanceId ().getId ();
        String userDomain = dataManager.getUserDomain ();
        String userEmail = dataManager.getUserEmail ();

        dataManager.updatePhoneNumber (phoneNumField);
        dataManager.updateDescription (descriptionField);

        RetrofitService retrofitService = new RetrofitService ();

        JsonApiInstances jsonApiInstances = retrofitService.getRetrofit ().create (JsonApiInstances.class);
        dataManager.getInstanceOfTypeUser ().setCreatedTimestamp (null);
        Call<Void> call = jsonApiInstances.updateInstanceTypeUser (instanceDomain, instanceId, userDomain, userEmail, dataManager.getInstanceOfTypeUser ());

        call.enqueue (new Callback<Void> () {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful ()) {
                    Log.d ("pttt", "" + response.code ());
                }
                Log.d ("pttt", "Success!!! desceiption and phone updated");
                updateUserRoleType ();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d ("pttt", "Failure!!!, Message: " + t.getMessage ());

            }
        });
    }

    private void startMainPageActivity() {
        String userBoundaryJson = new Gson ().toJson (dataManager.getUserBoundary ());
        String instanceBoundaryJson = new Gson ().toJson (dataManager.getInstanceOfTypeUser ());
        Intent intent = new Intent (this, MainPageActivity.class);
        Bundle bundle = new Bundle ();
        bundle.putString (getString (R.string.BUNDLE_USER_BOUNDARY_KEY), userBoundaryJson);
        bundle.putString (getString (R.string.BUNDLE_USER_INSTANCE_BOUNDARY_KEY), instanceBoundaryJson);
        intent.putExtras (bundle);
        SplashActivity.singleSplashActivity.finish();
        startActivity (intent);
    }

    private void startSplashActivity() {
        Intent splashIntent = new Intent (this, SplashActivity.class);
        startActivity (splashIntent);
    }

    private void findViews() {
        editProfile_TF_Name = findViewById (R.id.editProfile_TF_Name);
        editProfile_TF_phoneNumber = findViewById (R.id.editProfile_TF_phoneNumber);
        editProfile_TF_Description = findViewById (R.id.editProfile_TF_Description);
        editProfile_BTN_applyChanges = findViewById (R.id.editProfile_BTN_applyChanges);
    }
}
