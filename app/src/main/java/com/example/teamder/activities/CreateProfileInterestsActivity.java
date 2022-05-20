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
import com.example.teamder.logic.RoleType;
import com.example.teamder.models.InstanceOfTypeUser;
import com.example.teamder.models.InstanceType;
import com.example.teamder.models.NewUserBoundary;
import com.example.teamder.models.UserBoundary;
import com.example.teamder.models.UserId;
import com.example.teamder.retrofit.RetrofitService;
import com.example.teamder.service.JsonApiInstances;
import com.example.teamder.service.JsonApiUsers;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO: put all server methods in package server
public class CreateProfileInterestsActivity extends AppCompatActivity {
    private MaterialButton createProfileInterests_BTN_finish;
    private Spinner createProfileDesc_SPIN_spinnerTags;

    private DataManager dataManager;
    private Bundle bundle;
    private String userDesc;
    private String tutorialsName;
    private ArrayList<String> tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_create_profile_interests);
        dataManager = new DataManager ();

        findViews ();
        getNewUserBoundaryDetails ();
        initSpinner();

        setListeners ();
    }

    private void initSpinner() {
        //TODO: CONTINUE THE FLOW --> CREATE INTSNCE (POST) ADD INTERFACE FOR INSTANCES AND DO A POST
        tags = new ArrayList<> ();
        //TODO: function for this & put strings in R string
        //TODO: Add couple of tags and not only one(spinner by default adds only one), switch to another list
        ArrayList<String> arrayList = new ArrayList<> ();
        arrayList.add ("Arts");
        arrayList.add ("Electronics");
        arrayList.add ("Programming");
        arrayList.add ("Music");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        createProfileDesc_SPIN_spinnerTags.setAdapter (arrayAdapter);
        createProfileDesc_SPIN_spinnerTags.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tutorialsName = parent.getItemAtPosition (position).toString ();
                tags.add (tutorialsName);
                Toast.makeText (parent.getContext (), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show ();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getInstanceBoundaryDesc() {
        bundle = getIntent ().getExtras ();
        userDesc = bundle.getString (getString (R.string.BUNDLE_INSTANCE_BOUNDARY_KEY));
    }

    private void getNewUserBoundaryDetails() {
        bundle = getIntent ().getExtras ();
        String json = bundle.getString (getString (R.string.BUNDLE_NEW_USER_BOUNDARY_KEY));
        dataManager.setNewUserBoundary (new Gson ().fromJson (json, NewUserBoundary.class));
    }

    private void setListeners() {
        createProfileInterests_BTN_finish.setOnClickListener (view -> {
            getInstanceBoundaryDesc ();
            /*Start flow of creating :
            (1) UserBoundary
            (2) InstanceBoundary of the User
            (3) Change RoleType of the User to MANAGER from PLAYER
            (4) Change RoleType of the User to PLAYER again
             */
            createUserBoundary ();
        });
    }

    private void startMainPageActivity() {
        String userBoundaryJson = new Gson ().toJson(dataManager.getUserBoundary ());
        String instanceBoundaryJson = new Gson ().toJson(dataManager.getInstanceOfTypeUser ());
        Intent intent = new Intent (this, MainPageActivity.class);
        Bundle bundle =new Bundle ();
        bundle.putString(getString(R.string.BUNDLE_USER_BOUNDARY_KEY),userBoundaryJson);
        bundle.putString(getString(R.string.BUNDLE_USER_INSTANCE_BOUNDARY_KEY),instanceBoundaryJson);
        intent.putExtras(bundle);
        startActivity (intent);
    }

    private void createInstanceBoundaryOfTypeUser() {
        //TODO: move this to data mangaer
        String name = dataManager.getUserIdFromUserBoundary ();
        UserId userId = dataManager.getUserBoundary ().getUserId ();

        //TODO: CHECK WHICH TYPE OF INSTANCE IS IT
        // public InstanceOfTypeUser(String name, String type, UserId userId,String description, ArrayList<String> tags) {

        dataManager.setInstanceOfTypeUser (new InstanceOfTypeUser (name, InstanceType.USER.toString (), userId, userDesc, tags));

        RetrofitService retrofitService = new RetrofitService ();

        JsonApiInstances jsonApiInstances = retrofitService.getRetrofit ().create (JsonApiInstances.class);
        Call<InstanceOfTypeUser> call = jsonApiInstances.createInstanceUser (dataManager.getInstanceOfTypeUser ());

        call.enqueue (new Callback<InstanceOfTypeUser> () {
            @Override
            public void onResponse(Call<InstanceOfTypeUser> call, Response<InstanceOfTypeUser> response) {
                if (!response.isSuccessful ()) {
                    Log.d ("pttt", "" + response.code ());
                }
                Log.d ("pttt", "Success!!!, Created instance of type user");
                dataManager.setInstanceOfTypeUser (response.body ());
                updateUserRoleType();

            }

            @Override
            public void onFailure(Call<InstanceOfTypeUser> call, Throwable t) {
                Log.d ("pttt", "Failure!!!, Message: " + t.getMessage ());
            }
        });
    }

    /**
     * Creates new NewUserBoundary and retrieves from server UserBoundary
     * Input: New User Boundary (From this client)
     * Output: User Boundary (From server)
     */
    private void createUserBoundary() {
        RetrofitService retrofitService = new RetrofitService ();

        JsonApiUsers jsonApiUsers = retrofitService.getRetrofit ().create (JsonApiUsers.class);
        Call<UserBoundary> call = jsonApiUsers.createUser (dataManager.getNewUserBoundary ());
        call.enqueue (new Callback<UserBoundary> () {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                if (!response.isSuccessful ()) {
                    Log.d ("pttt", "" + response.code ());
                }
                dataManager.setUserBoundary (response.body ());
                Log.d ("pttt", "Success!!!, Message: " + dataManager.getUserBoundary ().getUsername ());
                Log.d ("pttt", "Success!!!, Message domain : " + dataManager.getUserBoundary ().getUserId ().getDomain ());
                updateUserRoleType ();
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                Log.d ("pttt", "Failure!!!, Message: " + t.getMessage ());
            }
        });
    }


    private void updateUserRoleType() {
        String userDomain = dataManager.getUserDomain ();
        String userEmail = dataManager.getUserEmail ();

        dataManager.updateUserRoleTypeData ();

        RetrofitService retrofitService = new RetrofitService ();

        JsonApiUsers jsonApiUsers = retrofitService.getRetrofit ().create (JsonApiUsers.class);

        Call<Void> call = jsonApiUsers.updateUser (userDomain, userEmail, dataManager.getUserBoundary ());
        call.enqueue (new Callback<Void> () {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful ()) {
                    Log.d ("pttt", "" + response.code ());
                }
                if(dataManager.getUserBoundaryRoleType ().equals (RoleType.MANAGER.toString ())) {
                    Log.d ("pttt", "Success!!! user role updated to manager");
                    createInstanceBoundaryOfTypeUser ();
                }
                else {
                    startMainPageActivity ();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d ("pttt", "Failure!!!, Message: " + t.getMessage ());
            }
        });
    }


    private void findViews() {
        createProfileInterests_BTN_finish = findViewById (R.id.createProfileInterests_BTN_finish);
        createProfileDesc_SPIN_spinnerTags = findViewById (R.id.createProfileDesc_SPIN_spinnerTags);

    }
}