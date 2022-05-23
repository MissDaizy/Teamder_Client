package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.example.teamder.ui.Group.GroupAdapter;
import com.example.teamder.ui.notifications.NotificationsFragment;
import com.google.android.material.button.MaterialButton;

public class ViewAllMyTeamsActivity extends AppCompatActivity {

    private MaterialButton list_BTN_viewGroupBTN;
    private MaterialButton groups_addGroup;
    private MaterialButton groups_all;
    private MaterialButton groups_manager;
    private MaterialButton group_member;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: check
        setContentView(R.layout.fragment_notifications);
        if(savedInstanceState != null){

            // load the fragment NotificationFragment to the @+od/container
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.container,
                    NotificationsFragment.newInstance()).commitNow();
        }

//TODO: delete?...
//        findViews();
//        setListeners();
    }



/*
    private void findViews() {

        list_BTN_viewGroupBTN = findViewById(R.id.list_BTN_viewGroupBTN);
        groups_addGroup = findViewById(R.id.groups_addGroup);
        list_BTN_viewGroupBTN = findViewById(R.id.list_BTN_viewGroupBTN);
        list_BTN_viewGroupBTN = findViewById(R.id.list_BTN_viewGroupBTN);
        list_BTN_viewGroupBTN = findViewById(R.id.list_BTN_viewGroupBTN);
    }

    private void setListeners() {
        //add new group
        groups_addGroup.setOnClickListener(v -> {
            Intent intent = new Intent (this, CreateGroupActivity.class);
            startActivity (intent);
        });

        //Show one group
        list_BTN_viewGroupBTN.setOnClickListener(v -> {
            Intent intent = new Intent (this, ViewChosenGroupActivity.class);
            startActivity (intent);
        });


    }

 */
}
