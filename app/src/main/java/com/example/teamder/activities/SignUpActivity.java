package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.example.teamder.models.RoleType;
import com.example.teamder.models.NewUserBoundary;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

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
            startCreateProfileActivity();
        });

        signUp_TXT_signIn.setOnClickListener (view -> {
            Intent intent =new Intent (this,LoginActivity.class);
            startActivity (intent);
        });
    }

    //create new NewUserBoundary in next activity and retrieve from server userBoundary
    private void startCreateProfileActivity() {
        String userEmail = signUp_TF_userEmail.getText().toString();
        Log.d ("pttt", "email from signup: "+userEmail);
        String json = new Gson ().toJson( new NewUserBoundary (userEmail,RoleType.PLAYER.toString ()));
        Intent intent =new Intent (this,CreateProfileActivity.class);
        Bundle bundle =new Bundle ();
        bundle.putString(getString(R.string.BUNDLE_NEW_USER_BOUNDARY_KEY),json);
        intent.putExtras(bundle);
        startActivity (intent);
    }


    private void findViews() {
        signUp_TF_userEmail=findViewById (R.id.signUp_TF_userEmail);
        signUp_TF_userPassword=findViewById (R.id.signUp_TF_userPassword);
        signUp_TF_reEnterPassword=findViewById (R.id.signUp_TF_reEnterPassword);
        signUp_BTN_signUpBtn=findViewById (R.id.signUp_BTN_signUpBtn);
        signUp_TXT_signIn=findViewById (R.id.signUp_TXT_signIn);
    }
}