package com.example.teamder.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.teamder.R;
import com.example.teamder.logic.DataManager;
import com.example.teamder.models.InstanceOfTypeUser;
import com.example.teamder.models.UserBoundary;
import com.example.teamder.retrofit.RetrofitService;
import com.example.teamder.service.ApiCallback;
import com.example.teamder.service.JsonApiEnhanced;
import com.example.teamder.service.JsonApiUsers;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    private String userEmail;
    private String userPassword;

    private DataManager dataManager;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_splash);

        dataManager=new DataManager ();

        doRequest (new ApiCallback () {
            @Override
            public void onSuccess(UserBoundary userBoundary) {

            }
        });

       // getLoginDetails ();

       // onResume ();
    }

    private void getLoginDetails() {
        bundle = getIntent ().getExtras ();
        userEmail = bundle.getString (getString (R.string.BUNDLE_USER_EMAIL_LOGIN_KEY));
        userPassword = bundle.getString (getString (R.string.BUNDLE_USER_PASSWORD_LOGIN_KEY));
    }

    public void doRequest(final ApiCallback callback) {
        RetrofitService retrofitService = new RetrofitService ();

        JsonApiUsers jsonApiUsers = retrofitService.getRetrofit ().create (JsonApiUsers.class);
        Call<UserBoundary> call = jsonApiUsers.getUserBoundary ("2022b.diana.ukrainsky", userEmail);
        call.enqueue (new Callback<UserBoundary> () {
            // If success
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                if (!response.isSuccessful ()) {
                    Log.d ("pttt", "" + response.code ());
                    showMessage();
                }
                else {
                    UserBoundary userBoundary = response.body ();
                    callback.onSuccess (userBoundary); // pass the list
                }
            }

            // If failed
            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                // Log error here since request failed
                Log.e ("pttt", t.toString ());
            }
        });
    }

    private void showMessage() {
        Toast.makeText(this, "User not exist, please sign up.", Toast.LENGTH_LONG).show();
    }


    public void onResume() {
        super.onResume ();
        doRequest (new ApiCallback () {
            @Override
            public void onSuccess(UserBoundary userBoundary) {
                dataManager.setUserBoundary (userBoundary);
                getInstanceOfTypeUser();
            }
        });
    }

    private void getInstanceOfTypeUser() {
        String name=dataManager.getUserIdFromUserBoundary ();
        RetrofitService retrofitService = new RetrofitService ();

        JsonApiEnhanced jsonApiEnhanced = retrofitService.getRetrofit ().create (JsonApiEnhanced.class);
        Call<List<InstanceOfTypeUser>> call = jsonApiEnhanced.getInstanceByName (name,dataManager.getUserDomain (),dataManager.getUserEmail (),10,0);

        call.enqueue (new Callback<List<InstanceOfTypeUser>> () {
            @Override
            public void onResponse(Call<List<InstanceOfTypeUser>> call, Response<List<InstanceOfTypeUser>> response) {
                if(!response.isSuccessful ()) {
                    Log.d ("pttt", "" + response.code ());
                }
                dataManager.setInstanceOfTypeUser (response.body ().get (0));
                Log.d ("pttt", "description :"+dataManager.getUserDescription ());
                startMainActivity ();
            }

            @Override
            public void onFailure(Call<List<InstanceOfTypeUser>> call, Throwable t) {
                Log.d ("pttt", "Failure!!!, Message: " + t.getMessage ());
            }
        });
    }

    private void startMainActivity() {
        String userBoundaryJson = new Gson ().toJson (dataManager.getUserBoundary ());
        String instanceTypeUserJson = new Gson ().toJson (dataManager.getInstanceOfTypeUser ());
        Intent intent = new Intent (this, MainPageActivity.class);
        Intent splashIntent = new Intent (this, SplashActivity.class);
        Bundle bundle = new Bundle ();
        bundle.putString (getString (R.string.BUNDLE_USER_BOUNDARY_KEY), userBoundaryJson);
        bundle.putString (getString (R.string.BUNDLE_USER_INSTANCE_BOUNDARY_KEY), instanceTypeUserJson);
        intent.putExtras (bundle);

        startActivity (intent);
    }

}