package com.example.teamder.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamder.R;
import com.example.teamder.ui.Group.GroupAdapter;

public class ViewAllGroups extends AppCompatActivity {

    private RecyclerView groupsAsManager_LST_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_view_all_groups);

        findViews();
        setListeners();
    }

    private void setListeners() {

    }

    private void findViews() {
        groupsAsManager_LST_items = findViewById(R.id.groupsAsManager_LST_items);
        /*
        TODO: IMPORTANT HERE , before creating the adapter
        set the groups
         */

//        GroupAdapter adapter = new GroupAdapter(this, allGroups);
//        groupsAsManager_LST_items.setAdapter();
//        groupsAsManager_LST_items.setLayoutManager(new ListLayoutManager(this));
    }
}
