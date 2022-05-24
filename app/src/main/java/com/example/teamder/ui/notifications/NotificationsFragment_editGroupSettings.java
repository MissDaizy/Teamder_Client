package com.example.teamder.ui.notifications;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.teamder.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class NotificationsFragment_editGroupSettings extends Fragment {

    // Image
    private ImageView editGroup_IMG_Close;

    // Number Picker
    private NumberPicker createTeamGroupNext_numberOfMembersPicker;

    // Text editors
    private MaterialTextView createTeamGroupNext_TXT_groupName;
    private TextInputEditText editGroup_TXT_editProjectName;
    private TextInputEditText editGroup_TXT_editDescription;
    private MaterialTextView editGroup_TXT_showTags;

    // Buttons
    private MaterialButton editGroup_BTN_applyChanges;
    // Tags:
    private MaterialButton editGroup_BTN_ShowTags;
    private AlertDialog tagsDialog;
    private AlertDialog.Builder tagsBuilder;
    private final CharSequence[] tagsList =
            {"Drawing", "sculpture", "Soldering",
                    "Book Readers", "Business",
                    "aerobic", "Anaerobic",
                    "Photographers", "Catering", "Halls", "Make Up", "Hair Stylist",
                    "Java", "Python", "C", "React.js",
                    "DJ", "Classic Music", "Rock Music",
                    "Meditation", "Yoga", "Breathing Meditation",
                    "Morning Runners", "Evening Runners",
                    "Mountaineering", "Stream Trip",
                    "Nature Photography", "Animal Photography", "Couple Photography", "Portrait Photography"};
    private final ArrayList selectItems = new ArrayList();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "edit group execute", Toast.LENGTH_SHORT).show();

        View view = inflater.inflate(R.layout.activity_edit_group, container, false);
        findViews(view);
        setListeners();
        return view;
    }

    private void setListeners() {

        editGroup_BTN_applyChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: DIANCHIK - save the changes in DB

                // Go to "Group" page view
                Fragment backToGroups = new NotificationsFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, backToGroups);
                fragmentTransaction.commit();
            }
        });

        editGroup_IMG_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to "chosen group" view
                Fragment viewGroup = new NotificationsFragment_viewChosen();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, viewGroup);

                //TODO: option go back to the "Group" first frag
                fragmentTransaction.addToBackStack("NotificationsFragment");

                fragmentTransaction.commit();
            }
        });

        editGroup_BTN_ShowTags.setOnClickListener(v -> {
            //set the spinner

            showTags();
        });

    }

    private void showTags() {
        // TODO: Init alertdialog builder >> from create group next

 /*TODO: init alertdialog in fragment
        Context context ;

        tagsBuilder = new AlertDialog.Builder(context);
        tagsBuilder.setTitle("Your Interests").setMultiChoiceItems(tagsList, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                if(isChecked){
                    // Add to selected items
                    selectItems.add(tagsList[position]);
                }else if (existInList()){
                    // TODO: need fixing::: If the Item is already in the list >> delete from selected items
                    selectItems.remove(Integer.valueOf(position));
                }

            }
        });

        // Show Tags in TextView
        printChoosenTags();

        // Create Dialog
        tagsDialog = tagsBuilder.create();
        tagsDialog.show();

  */

    }
 /*TODO: init alertdialog in fragment

    private void printChoosenTags() {
        // Show Tags in TextView
        tagsBuilder.setPositiveButton("Selected Items", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuilder sb = new StringBuilder();
                for (Object tagsList:selectItems){
                    sb.append(tagsList.toString() + "\n");
                }
                editGroup_TXT_showTags.setText(sb.toString());
            }
        });
    }

    private boolean existInList(){
        boolean exist = false;
        for(int i=0; i < tagsList.length; i++) {
            if(selectItems.equals(tagsList[i])){
                exist = true;
            }
        }

        return exist;
    }

  */

    private void findViews(View view) {
        // Image
        editGroup_IMG_Close = view.findViewById(R.id.editGroup_IMG_Close);

        // Number picker
        createTeamGroupNext_numberOfMembersPicker = view.findViewById(R.id.createTeamGroupNext_TXT_groupName);

        // Text editors
        createTeamGroupNext_TXT_groupName = view.findViewById(R.id.createTeamGroupNext_TXT_groupName);
        editGroup_TXT_editProjectName = view.findViewById(R.id.editGroup_TXT_editProjectName);
        editGroup_TXT_editDescription = view.findViewById(R.id.editGroup_TXT_editDescription);
        editGroup_TXT_showTags = view.findViewById(R.id.editGroup_TXT_showTags);

        // Buttons
        editGroup_BTN_ShowTags = view.findViewById(R.id.editGroup_BTN_ShowTags);
        editGroup_BTN_applyChanges = view.findViewById(R.id.editGroup_BTN_applyChanges);
    }
}
