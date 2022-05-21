package com.example.teamder.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamder.R;
import com.example.teamder.models.InstanceOfTypeGroup;

import java.util.ArrayList;

public class ViewAllGroupsAsManager extends AppCompatActivity {

    // Connecting to the RecyclerView
    // that in the layout: activity_view_all_groups
    private RecyclerView groupsAsManager_LST_items;

    // Local variable of all groups from DB
    private ArrayList<InstanceOfTypeGroup> allGroups = new ArrayList<>();

    // Array af all group images from drawable package
    // TODO: delete if not used :)
    ArrayOfGroupsPictures groupImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sign_up);

        findViews();
        setListeners();
    }

    private void setListeners() {

    }

    private void findViews() {
        groupsAsManager_LST_items = findViewById(R.id.groupsAsManager_LST_items);
    }
}
