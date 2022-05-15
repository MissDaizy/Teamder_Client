package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class SignUpActivity extends AppCompatActivity {
    private EditText signUp_TF_userEmail;
    private EditText signUp_TF_userPassword;
    private EditText signUp_TF_reEnterPassword;
    private MaterialTextView signUp_TXT_signIn;
    private MaterialButton signUp_BTN_signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sign_up);

        findViews();
        setListeners();
    }

    private void setListeners() {
        signUp_BTN_signUpBtn.setOnClickListener (view -> {
            Intent intent =new Intent (this,CreateProfileActivity.class);
            startActivity (intent);
        });

        signUp_TXT_signIn.setOnClickListener (view -> {
            Intent intent =new Intent (this,LoginActivity.class);
            startActivity (intent);
        });
    }

    private void findViews() {
        signUp_TF_userEmail=findViewById (R.id.login_TF_userEmail);
        signUp_TF_userPassword=findViewById (R.id.login_TF_userPassword);
        signUp_TF_reEnterPassword=findViewById (R.id.signUp_TF_reEnterPassword);
        signUp_BTN_signUpBtn=findViewById (R.id.signUp_BTN_signUpBtn);
        signUp_TXT_signIn=findViewById (R.id.signUp_TXT_signIn);
    }
}