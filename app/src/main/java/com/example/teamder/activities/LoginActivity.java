package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.example.teamder.logic.DataManager;
import com.example.teamder.models.InstanceOfTypeUser;
import com.example.teamder.models.UserBoundary;
import com.example.teamder.retrofit.RetrofitService;
import com.example.teamder.service.ApiCallback;
import com.example.teamder.service.JsonApiEnhanced;
import com.example.teamder.service.JsonApiUsers;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText login_TF_userEmail;
    private EditText login_TF_userPassword;
    private MaterialTextView login_TXT_signUp;
    private MaterialButton login_BTN_loginBtn;

    private String userEmail;
    private String userPassword;

    private DataManager dataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        dataManager=new DataManager ();

        findViews();
        setListeners();
    }

    private void setListeners() {
        login_TXT_signUp.setOnClickListener (view -> {
            Intent intent = new Intent (this,SignUpActivity.class);
            startActivity (intent);
        });

        login_BTN_loginBtn.setOnClickListener(view -> {
            //DO NOT delete comment of find user in here please:
            // startSplashActivity();
           // userEmail=login_TF_userEmail.getText().toString();

           findUser(login_TF_userEmail.getText().toString(),
                  login_TF_userPassword.getText().toString());

        });
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


    public void onResume() {
        super.onResume ();
        doRequest (new ApiCallback () {
            @Override
            public void onSuccess(UserBoundary userBoundary) {
                dataManager.setUserBoundary (userBoundary);
                getInstanceOfTypeUser();
                startMainActivity ();
            }
        });
    }

    private void startSplashActivity() {
        moveTaskToBack(true);

        String userEmail=login_TF_userEmail.getText().toString();
        String userPassword= login_TF_userPassword.getText().toString();
        Intent splashIntent = new Intent (this, SplashActivity.class);
        Bundle bundle = new Bundle ();
        bundle.putString (getString (R.string.BUNDLE_USER_EMAIL_LOGIN_KEY), userEmail);
        bundle.putString (getString (R.string.BUNDLE_USER_PASSWORD_LOGIN_KEY), userPassword);
        splashIntent.putExtras (bundle);
        startActivity (splashIntent);
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

    private void findUser(String userEmail, String userPassword) {
        //startSplashActivity();

        RetrofitService retrofitService = new RetrofitService ();

        JsonApiUsers jsonApiUsers = retrofitService.getRetrofit ().create (JsonApiUsers.class);
        Call<UserBoundary> call = jsonApiUsers.getUserBoundary ("2022b.diana.ukrainsky" ,userEmail);
        call.enqueue(new Callback<UserBoundary> () {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                if(!response.isSuccessful())
                    showMessage();
                else {
                    dataManager.setUserBoundary (response.body ());
                    getInstanceOfTypeUser();
                }
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                Log.d ("pttt", "Failure!!!, Message: " + t.getMessage ());
            }
        });

        /*
        TODO: search in data base if user exists WITH PASSWORD ALSO
         */
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

    private void showMessage() {
        Toast.makeText(this, "User not exist, please sign up.", Toast.LENGTH_LONG).show();
    }

    private void findViews() {
        login_TF_userEmail=findViewById (R.id.login_TF_userEmail);
        login_TF_userPassword=findViewById (R.id.login_TF_userPassword);
        login_TXT_signUp=findViewById (R.id.login_TXT_signUp);
        login_BTN_loginBtn=findViewById (R.id.login_BTN_loginBtn);
    }
}