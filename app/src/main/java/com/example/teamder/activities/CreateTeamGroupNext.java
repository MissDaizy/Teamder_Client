package com.example.teamder.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class CreateTeamGroupNext extends AppCompatActivity implements NumberPicker.OnValueChangeListener, AdapterView.OnItemSelectedListener {

    //number of members in group
    private NumberPicker createTeamGroupNext_TXT_numOfMembers;
    private TextView showNumbers;

    //spinner
    private Spinner createTeamGroupNext_SPN_topics;
    private List<String> listOfTopics;
    private ArrayAdapter<CharSequence> topicAddapter;

    //    private String view; >>>>> TODO:string buffer.
    private final int MAX_NUMBER_OF_TAGS = 40;
    private int currentNumberOfTags = 0;
    private String[] tagsArray;
    private TextView createTeamGroupNext_TXT_tagsView;

    // Open group
    private MaterialButton createTeamGroupNext_BTN_openGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_create_teap_group_next);

        findViews();
        setNumberPicker();
        setSpinner();
        setBottonListener();

    }

    private void setBottonListener() {
        createTeamGroupNext_BTN_openGroup.setOnClickListener(view -> {
            //TODO:new intent, when is going after.
            //TODO: DIANCHIK's <spring:POST>
        });
    }

    //TODO:implement to show tags in TextView
    private void showSelectedTags() {
        for(int i = 0; i < currentNumberOfTags; i++){
            /*
            TODO: string buffer for all tags to be printed.
            or change averything with the tags to multiple
             */
            createTeamGroupNext_TXT_tagsView.setText(tagsArray[i]);
        }

    }

    private void setSpinner() {
        //setTheCategories();

        //style and populate the spinner
        topicAddapter = ArrayAdapter.createFromResource(this, R.array.topics,android.R.layout.simple_spinner_item);
        //dropdown layout style
        topicAddapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        createTeamGroupNext_SPN_topics.setAdapter(topicAddapter);
        createTeamGroupNext_SPN_topics.setOnItemSelectedListener(this);
    }
//delete
 /*   private void setTheCategories() {
        listOfTopics = new ArrayList<>();
        listOfTopics.add(0, "Choose Topic");
        listOfTopics.add("Art");
        listOfTopics.add("Fitness");
        listOfTopics.add("Inspiration");
        listOfTopics.add("Photography");
        listOfTopics.add("Programming");
        listOfTopics.add("Meditation");
        listOfTopics.add("Music");
        listOfTopics.add("Running");
        listOfTopics.add("Traveling");
        listOfTopics.add("Weddings");
    }

  */

    private void setNumberPicker() {
        createTeamGroupNext_TXT_numOfMembers.setMaxValue(100);
        createTeamGroupNext_TXT_numOfMembers.setMinValue(0);
        createTeamGroupNext_TXT_numOfMembers.setOnValueChangedListener(this);
//delete
/*        createTeamGroupNext_TXT_numOfMembers = new NumberPicker(this);
        //   LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //  numberPicker.setLayoutParams(layoutParams);

        createTeamGroupNext_TXT_numOfMembers.setMinValue(0);
        createTeamGroupNext_TXT_numOfMembers.setMaxValue(10);
        createTeamGroupNext_TXT_numOfMembers.setWrapSelectorWheel(true);
        createTeamGroupNext_TXT_numOfMembers.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                String text = "Changed from " + oldVal + " to " + newVal;
                Toast.makeText(CreateTeamGroupNext.this, text, Toast.LENGTH_SHORT).show();
            }


        });

 */
    }

    private void findViews() {
        showNumbers = findViewById(R.id.ShowNumbers);
        createTeamGroupNext_TXT_numOfMembers = findViewById(R.id.createTeamGroupNext_numberOfMembersPicker);
        createTeamGroupNext_SPN_topics = findViewById(R.id.createTeamGroupNext_SPN_tags);
        createTeamGroupNext_TXT_tagsView = findViewById(R.id.createTeamGroupNext_TXT_tagsView);
        createTeamGroupNext_BTN_openGroup  = findViewById(R.id.createTeamGroupNext_BTN_openGroup);
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int j) {
        showNumbers.setText("Old value = " + i + "\nNew Value = " + j);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if(adapterView.getItemAtPosition(position).equals("Choose Topic")){
            //do nothing!!!
        }
        else{
            // On selecting a spinner item
            String item = adapterView.getItemAtPosition(position).toString();

            // Show selected item
            Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();

            //TODO: DIANCHIK's

        }
//maybe use when work with string buffer
/*
        String text = parent.getItemAtPosition(i).toString();
        tagsArray = new String[MAX_NUMBER_OF_TAGS];
        tagsArray[currentNumberOfTags] = text;
        currentNumberOfTags++;
        showSelectedTags();

 */

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
