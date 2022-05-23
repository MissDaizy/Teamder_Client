package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class ViewChosenGroupActivity extends AppCompatActivity {

    private MaterialButton viewGroup_BTN_editBTN;
    private MaterialButton viewGroup_BTN_deleteBTN;
    private ImageView viewGroup_IMG_GroupImage;
    private MaterialTextView createProfileDesc_TXT_TeamName;
    private MaterialTextView viewGroup_TXT_topic;
    private MaterialTextView viewGroup_TXT_tags;
    private TextView viewGroup_TXT_maxNumOfMembersInTeam;
    private MaterialButton viewGroup_BTN_exitView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_choosen_team);

        findViews();
        setListeners();
    }

    private void setListeners() {
        viewGroup_BTN_editBTN.setOnClickListener(v -> {
            Intent intent = new Intent (this, EditGroupActivity.class);
            startActivity (intent);
        });

        viewGroup_BTN_deleteBTN.setOnClickListener(v -> {
            //TODO: (DIANCHIK's)
            // 1) delete this team group from DB,
            // 2) update the view
        });
    }

    private void findViews() {
        viewGroup_BTN_editBTN = findViewById(R.id.viewGroup_BTN_editBTN);
        viewGroup_BTN_deleteBTN = findViewById(R.id.viewGroup_BTN_deleteBTN);
        viewGroup_IMG_GroupImage = findViewById(R.id.viewGroup_IMG_GroupImage);
        createProfileDesc_TXT_TeamName = findViewById(R.id.createProfileDesc_TXT_TeamName);
        viewGroup_TXT_topic = findViewById(R.id.viewGroup_TXT_topic);
        viewGroup_TXT_tags = findViewById(R.id.viewGroup_TXT_tags);
        viewGroup_TXT_maxNumOfMembersInTeam = findViewById(R.id.viewGroup_TXT_maxNumOfMembersInTeam);
        viewGroup_BTN_exitView = findViewById(R.id.viewGroup_BTN_exitView);
    }
}
