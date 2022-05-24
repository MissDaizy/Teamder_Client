package com.example.teamder.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class EditGroupActivity extends AppCompatActivity {

    private ImageView editGroup_IMG_Close;
    private MaterialTextView createTeamGroupNext_TXT_groupName;
    private NumberPicker createTeamGroupNext_numberOfMembersPicker;
    private TextInputEditText editGroup_TXT_editProjectName;
    private TextInputEditText editGroup_TXT_editDescription;
    private MaterialButton editGroup_BTN_ShowTags;
    private MaterialTextView editGroup_TXT_showTags;
    private MaterialButton editGroup_BTN_applyChanges;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group);

        findViews();
        setListeners();
    }

    private void setListeners() {

        editGroup_BTN_applyChanges.setOnClickListener(v->{
            //TODO: go to "Group" page view
        });

        editGroup_IMG_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: go to "chosen group" view
            }
        });

        editGroup_BTN_ShowTags.setOnClickListener(v -> {
            //set the spinner

            showTags();
        });

        editGroup_BTN_applyChanges.setOnClickListener(v -> {
            //save all changes of editing
            //TODO: DIANCHIK: save on DB

            //return to show this group with changes
//            Intent intent = new Intent (this, ViewChosenGroupActivity.class);
//            startActivity (intent);
        });
    }

    private void showTags() {
        // Init alertdialog builder

    }

    private void findViews() {
        createTeamGroupNext_TXT_groupName.findViewById(R.id.createTeamGroupNext_TXT_groupName);
        createTeamGroupNext_numberOfMembersPicker.findViewById(R.id.createTeamGroupNext_numberOfMembersPicker);
        editGroup_TXT_editProjectName.findViewById(R.id.editGroup_TXT_editProjectName);
        editGroup_TXT_editDescription.findViewById(R.id.editGroup_TXT_editDescription);
        editGroup_BTN_ShowTags.findViewById(R.id.editGroup_BTN_ShowTags);
        editGroup_TXT_showTags.findViewById(R.id.editGroup_TXT_showTags);
        editGroup_BTN_applyChanges.findViewById(R.id.editGroup_BTN_applyChanges);
        editGroup_IMG_Close.findViewById(R.id.editGroup_IMG_Close);
    }
}
