package com.example.teamder.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.example.teamder.logic.DataManager;
import com.example.teamder.models.InstanceOfTypeGroup;
import com.example.teamder.models.InstanceOfTypeUser;
import com.example.teamder.models.InstanceType;
import com.example.teamder.models.RoleType;
import com.example.teamder.models.UserBoundary;
import com.example.teamder.models.UserId;
import com.example.teamder.retrofit.RetrofitService;
import com.example.teamder.service.JsonApiInstances;
import com.example.teamder.service.JsonApiUsers;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTeamGroupNext extends AppCompatActivity implements NumberPicker.OnValueChangeListener, AdapterView.OnItemSelectedListener {

    // Number of members in group
    private NumberPicker createTeamGroupNext_TXT_numOfMembers;
    private TextView showNumbers;

    // Spinner
    private Spinner createTeamGroupNext_SPN_topics;
    private List<String> listOfTopics;
    private ArrayAdapter<CharSequence> topicAddapter;

    // Tags
    private Button createTeamGroupNext_BTN_ShowTags;
    private Button createTeamGroupNext_BTN_Clear;
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
    private TextView createTeamGroupNext_TXT_tagsView;

    // Open group
    private MaterialButton createTeamGroupNext_BTN_openGroup;

    private Bundle bundle;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_create_teap_group_next);

        dataManager=new DataManager ();
        bundle=new Bundle ();

        getUserBoundary();
        //getGroupDetails();

        findViews();
        setViews();
    }

    private void getUserBoundary() {
        bundle = getIntent ().getExtras ();
        String userBoundaryJson = bundle.getString (getString (R.string.BUNDLE_USER_BOUNDARY_KEY));
        String instanceBoundaryJson = bundle.getString (getString (R.string.BUNDLE_USER_INSTANCE_BOUNDARY_KEY));

        dataManager.setUserBoundary ((new Gson ().fromJson (userBoundaryJson, UserBoundary.class)));
        dataManager.setInstanceOfTypeUser (new Gson ().fromJson (instanceBoundaryJson, InstanceOfTypeUser.class));
        Log.d ("pttt", "description in main page :"+dataManager.getUserDescription ());
    }

    private void getGroupDetails() {
        bundle=getIntent ().getExtras ();
        String groupName=bundle.getString (getString(R.string.BUNDLE_GROUP_NAME_KEY));
        String groupDescription=bundle.getString (getString (R.string.BUNDLE_GROUP_DESCRIPTION_KEY));
    }

    private void setViews() {
        setNumberPicker();
        setTopicSpinner();
        showTagsButton();
        createTeamGroupButten();
        clearChoosedTags();
    }

    private void clearChoosedTags() {
        createTeamGroupNext_BTN_Clear.setOnClickListener(view -> {
            selectItems.clear();
            createTeamGroupNext_TXT_tagsView.setText("");
        });

    }

    private void showTagsButton() {
        createTeamGroupNext_BTN_ShowTags.setOnClickListener(view -> {
            showTags();
        });
    }

    private void showTags() {
        // Init alertdialog builder
        tagsBuilder = new AlertDialog.Builder(this);
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

    private void printChoosenTags() {
        // Show Tags in TextView
        tagsBuilder.setPositiveButton("Selected Items", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuilder sb = new StringBuilder();
                for (Object tagsList:selectItems){
                    sb.append(tagsList.toString() + "\n");
                }
                createTeamGroupNext_TXT_tagsView.setText(sb.toString());
            }
        });
    }

    private void createTeamGroupButten() {
        createTeamGroupNext_BTN_openGroup.setOnClickListener(view -> {
            updateUserRoleType();
            //TODO:new intent, when is going after.
            //TODO: DIANCHIK's <spring:POST>
        });
    }

    private void updateUserRoleType() {
        String userDomain = dataManager.getUserDomain ();
        String userEmail = dataManager.getUserEmail ();

        dataManager.updateUserRoleTypeData ();

        RetrofitService retrofitService = new RetrofitService ();

        JsonApiUsers jsonApiUsers = retrofitService.getRetrofit ().create (JsonApiUsers.class);

        Call<Void> call = jsonApiUsers.updateUser (userDomain, userEmail, dataManager.getUserBoundary ());
        call.enqueue (new Callback<Void> () {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful ()) {
                    Log.d ("pttt", "" + response.code ());
                }
                if(dataManager.getUserBoundaryRoleType ().equals (RoleType.MANAGER.toString ())) {
                    Log.d ("pttt", "Success!!! user role updated to manager");
                    createInstanceOfTypeGroup ();
                }
                else
                    startViewAllGroupsActivity();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d ("pttt", "Failure!!!, Message: " + t.getMessage ());
            }
        });
    }

    private void startViewAllGroupsActivity() {
        Intent intent=new Intent (this, ViewAllGroupsActivity.class);
        startActivity (intent);
    }

    private void createInstanceOfTypeGroup() {
            /*
        Get group attributes from bundle in previous activity(CreateTeamGroup)
         */
        bundle=getIntent ().getExtras ();
        String groupName=bundle.getString (getString(R.string.BUNDLE_GROUP_NAME_KEY));
        String groupDescription=bundle.getString (getString (R.string.BUNDLE_GROUP_DESCRIPTION_KEY));

        /*
        Get group attributes from this activity
         */
        int numOfMembers=createTeamGroupNext_TXT_numOfMembers.getValue ();
        String name = dataManager.getUserIdFromUserBoundary ();
        UserId userId = dataManager.getUserBoundary ().getUserId ();
        ArrayList<String> selectedItemsList=new ArrayList<> ();
        selectedItemsList=selectItems;

        // TODO: CHECK WHICH TYPE OF INSTANCE IS IT

        dataManager.setInstanceOfTypeGroup (new InstanceOfTypeGroup (name,groupName, InstanceType.GROUP.toString (), userId, groupDescription, selectedItemsList,numOfMembers));
        RetrofitService retrofitService = new RetrofitService ();

        JsonApiInstances jsonApiInstances = retrofitService.getRetrofit ().create (JsonApiInstances.class);
        Call<InstanceOfTypeGroup> call = jsonApiInstances.createInstanceGroup (dataManager.getInstanceOfTypeGroup ());

        call.enqueue (new Callback<InstanceOfTypeGroup> () {
            @Override
            public void onResponse(Call<InstanceOfTypeGroup> call, Response<InstanceOfTypeGroup> response) {
                if (!response.isSuccessful ()) {
                    Log.d ("pttt", "" + response.code ());
                }
                Log.d ("pttt", "Success!!!, Created instance of type user");
                dataManager.setInstanceOfTypeGroup (response.body ());
                updateUserLists();
            }

            @Override
            public void onFailure(Call<InstanceOfTypeGroup> call, Throwable t) {
                Log.d ("pttt", "Failure!!!, Message: " + t.getMessage ());
            }
        });

    }

    private void updateUserLists() {
        String instanceDomain = dataManager.getInstanceOfTypeUser ().getInstanceId ().getDomain ();
        String instanceId = dataManager.getInstanceOfTypeUser ().getInstanceId ().getId ();

        String userDomain = dataManager.getUserDomain ();
        String userEmail = dataManager.getUserEmail ();

        dataManager.updateUserListsAfterGroupCreationData ();

        RetrofitService retrofitService = new RetrofitService ();

        JsonApiInstances jsonApiInstances = retrofitService.getRetrofit ().create (JsonApiInstances.class);
        dataManager.getInstanceOfTypeUser ().setCreatedTimestamp (null);
        Call<Void> call = jsonApiInstances.updateInstanceTypeUser (instanceDomain, instanceId, userDomain, userEmail, dataManager.getInstanceOfTypeUser ());

        call.enqueue (new Callback<Void> () {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful ()) {
                    Log.d ("pttt", "" + response.code ());
                }
                Log.d ("pttt", "Success!!! desceiption and phone updated");
                updateUserRoleType ();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d ("pttt", "Failure!!!, Message: " + t.getMessage ());

            }
        });
    }

    private void setTopicSpinner() {
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
        createTeamGroupNext_BTN_ShowTags = findViewById(R.id.createTeamGroupNext_BTN_ShowTags);
        createTeamGroupNext_BTN_Clear = findViewById(R.id.createTeamGroupNext_BTN_Clear);
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