package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class CreateTeamGroup extends AppCompatActivity {

    private TextInputEditText createTeamGroup_TXT_setProjectName;
    private TextInputEditText createTeamGroup_TXT_setProjectDescription;
    private MaterialButton createTeamGroup_BTN_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_create_team_group);

        findViews();
        getStrings();
        setListener();
    }

    private void setListener() {
        createTeamGroup_BTN_next.setOnClickListener(view -> {
            Intent intent = new Intent(CreateTeamGroup.this, CreateTeamGroupNext.class);
            startActivity(intent);
        });
    }

    private void getStrings() {
        String groupName = createTeamGroup_TXT_setProjectName.getText ().toString ();
        String groupDescription = createTeamGroup_TXT_setProjectDescription.getText ().toString ();

    }

    private void findViews() {
        createTeamGroup_TXT_setProjectName = findViewById(R.id.createTeamGroup_TXT_setProjectName);
        createTeamGroup_TXT_setProjectDescription = findViewById(R.id.createTeamGroup_TXT_setProjectDescription);
        createTeamGroup_BTN_next = findViewById(R.id.createTeamGroup_BTN_next);
    }

}
