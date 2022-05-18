package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class LoginActivity extends AppCompatActivity {
    private EditText login_TF_userEmail;
    private EditText login_TF_userPassword;
    private MaterialTextView login_TXT_signUp;
    private MaterialButton login_BTN_loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        findViews();
        setListeners();
    }

    private void setListeners() {
        login_TXT_signUp.setOnClickListener (view -> {
            Intent intent = new Intent (this,SignUpActivity.class);
            startActivity (intent);
        });

        login_BTN_loginBtn.setOnClickListener(view -> {
            int login = findUser(login_TF_userEmail.getText().toString(),
                    login_TF_userPassword.getText().toString());

            if(login == 1){
                Intent intent = new Intent (this, MainPageActivity.class);
                startActivity (intent);
            }
            else{
                Toast.makeText(this, "User not exist, please sign up.", Toast.LENGTH_LONG).show();
            }
        });

    }

    private int findUser(String userEmail, String userPassword) {
        int exist = 1;


        /*
        TODO: search in data base if user exists
         */

        return exist;
    }

    private void findViews() {
        login_TF_userEmail=findViewById (R.id.login_TF_userEmail);
        login_TF_userPassword=findViewById (R.id.login_TF_userPassword);
        login_TXT_signUp=findViewById (R.id.login_TXT_signUp);
        login_BTN_loginBtn=findViewById (R.id.login_BTN_loginBtn);
    }


}