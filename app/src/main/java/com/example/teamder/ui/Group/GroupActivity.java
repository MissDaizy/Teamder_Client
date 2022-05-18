package com.example.teamder.ui.Group;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;

/*
Classes GroupActivity and GroupView
is for dynamic setting images as matrix
for Screen "HOME"
 */

public class GroupActivity extends AppCompatActivity {

    private LinearLayout lay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        setImages();
    }

    private void setImages() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                ImageView groupBTN = new ImageView(this);

            }
        }
    }
}
