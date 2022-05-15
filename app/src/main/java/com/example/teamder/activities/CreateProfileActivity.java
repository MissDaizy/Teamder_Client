package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.example.teamder.logic.JsonPlaceHolderApi;
import com.example.teamder.logic.DataManager;
import com.example.teamder.models.NewUserBoundary;
import com.example.teamder.models.UserBoundary;
import com.example.teamder.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateProfileActivity extends AppCompatActivity {
    private EditText createProfile_TF_firstName;
    private EditText createProfile_TF_lastName;
    private EditText createProfile_TF_phoneNumber;
    private RadioButton createProfile_RadioBtn_female;
    private RadioButton createProfile_RadioBtn_male;
    private MaterialButton createProfile_BTN_next;

    DataManager dataManager;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_create_profile);
        dataManager =new DataManager ();

        findViews();
        getNewUserBoundaryDetails();
        setListeners();
    }

    private void getNewUserBoundaryDetails() {
        bundle = getIntent().getExtras();
        String json = bundle.getString(getString(R.string.BUNDLE_NEW_USER_BOUNDARY_KEY));

        dataManager.setNewUserBoundary (new Gson ().fromJson(json, NewUserBoundary.class));
        Log.d ("pttt", "userManager"+ dataManager.getUserBoundary ().getUsername ());
    }

    /**
     * Creates new NewUserBoundary and retrieves from server UserBoundary
     *    Input: New User Boundary (From this client)
     *    Output: User Boundary (From server)
     */
    private UserBoundary createUserBoundary() {
        RetrofitService retrofitService=new RetrofitService ();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofitService.getRetrofit ().create(JsonPlaceHolderApi.class);
        Call<UserBoundary> call = jsonPlaceHolderApi.createUser(dataManager.getNewUserBoundary ());

        call.enqueue (new Callback<UserBoundary> () {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                if(!response.isSuccessful()){
                    Log.d ("pttt", ""+response.code());
                }
                dataManager.setUserBoundary (response.body ());
                Log.d ("pttt", "Success!!!, Message: "+ dataManager.getUserBoundary ().getUsername ());
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                Log.d ("pttt", "Failure!!!, Message: "+t.getMessage ());
            }
        });
    return dataManager.getUserBoundary ();
    }

    private void setListeners() {
        createProfile_BTN_next.setOnClickListener (view -> {
            startCreateProfileDescActivity();
        });
    }

    private void startCreateProfileDescActivity() {
        String userFirstName=createProfile_TF_firstName.getText().toString();
        String userLastName=createProfile_TF_lastName.getText ().toString ();
        String userPhoneNumber=createProfile_TF_phoneNumber.getText ().toString ();
        String username = userFirstName+" "+userLastName;

        dataManager.setNewUserBoundaryAvatarData("Client avatar path");
        dataManager.setNewUserBoundaryUsernameData(username);
        //Phone number save:
        //userManager.setNewUserBoundaryAvatarData("Client avatar path");
        createUserBoundary();
        String json = new Gson ().toJson(dataManager.getNewUserBoundary ());

        Intent intent = new Intent (this,CreateProfileDescActivity.class);
        Bundle bundle =new Bundle ();
        bundle.putString(getString(R.string.BUNDLE_USER_BOUNDARY_KEY),json);
        intent.putExtras(bundle);
        startActivity (intent);
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