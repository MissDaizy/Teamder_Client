package com.example.teamder.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamder.R;
import com.example.teamder.models.InstanceOfTypeGroup;
import com.example.teamder.ui.Group.GroupAdapter;
import com.example.teamder.ui.notifications.NotificationsFragment;

import java.util.ArrayList;

public class ViewAllGroupsActivity extends AppCompatActivity {

    // Connecting to the RecyclerView
    // that in the layout: activity_view_all_groups
    private RecyclerView allGroups_LST_items;

    // Local variable of all groups from DB
    private ArrayList<InstanceOfTypeGroup> allGroups = new ArrayList<>();

    // Array af all group images from drawable package
    // TODO: delete if not used :)
    ArrayOfGroupsPictures groupImages;

    //frag
    private NotificationsFragment notificationsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_view_all_groups);

        findViews();
        //TODO: IMPORTANT set the groups HERE , before creating the adapter
        setAllGroups();
        // "Call" the adapter to show all the list
        createAdapter();
        setListeners();

        //frag
 //       setFragments();
    }

    private void setFragments() {
        notificationsFragment = new NotificationsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.Linear_LST_groups, notificationsFragment).commit();
    }

    private void createAdapter() {
//        //maybe need to close this. if the comment isn't relevant: erase the comment
//        GroupAdapter adapter = new GroupAdapter(this, allGroups);
//        allGroups_LST_items.setAdapter(adapter);
//        allGroups_LST_items.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setAllGroups(){
        /*
        TODO: DIANCHIK's:
        here "build" the array of all groups that exists (or not) in the DB
         */
    }

    private void setListeners() {

    }

    private void findViews() {
        allGroups_LST_items = findViewById(R.id.groupsAll_LST_items);


 }
}
