package com.example.teamder.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import com.example.teamder.R;

public class SplashActivity extends AppCompatActivity {
    public static Activity singleSplashActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        singleSplashActivity =this;
        setContentView (R.layout.activity_splash);
    }
}