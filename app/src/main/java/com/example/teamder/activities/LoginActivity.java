package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.example.teamder.logic.DataManager;
import com.example.teamder.models.UserBoundary;
import com.example.teamder.retrofit.RetrofitService;
import com.example.teamder.service.JsonApiUsers;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText login_TF_userEmail;
    private EditText login_TF_userPassword;
    private MaterialTextView login_TXT_signUp;
    private MaterialButton login_BTN_loginBtn;

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
            findUser(login_TF_userEmail.getText().toString(),
                    login_TF_userPassword.getText().toString());

//            if(login == 1){
//                startMainActivity();
//            }
//            else{
//                Toast.makeText(this, "User not exist, please sign up.", Toast.LENGTH_LONG).show();
//            }

        });
    }

    private void startMainActivity() {
        String json = new Gson ().toJson (dataManager.getUserBoundary ());

        Intent intent = new Intent (this, MainPageActivity.class);
        Bundle bundle = new Bundle ();
        bundle.putString (getString (R.string.BUNDLE_USER_BOUNDARY_KEY), json);
        intent.putExtras (bundle);
        startActivity (intent);
    }

    private void findUser(String userEmail, String userPassword) {
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
                    startMainActivity ();
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