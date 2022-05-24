package com.example.teamder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teamder.R;
import com.example.teamder.logic.DataManager;
import com.example.teamder.models.InstanceOfTypeUser;
import com.example.teamder.models.UserBoundary;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ViewProfileActivity extends AppCompatActivity {

    private ImageView ViewProfile_IMG_logo;
    private TextView ViewProfile_TXT_userEmail;
    private TextView ViewProfile_TXT_userName;
    private TextView ViewProfile_TXT_PhoneNumber;
    private TextView ViewProfile_TXT_ProfileDesc;
    private TextView ViewProfile_TXT_interestsTags;
    private Button ViewProfile_BTN_editBtn;
    private ImageView editProfile_IMG_Close;

    private DataManager dataManager;
    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_view_user_profile);

        bundle=new Bundle ();
        dataManager=new DataManager ();

        findViews();

        getUserBoundary ();
        getUserInstance ();
        initFields ();
        setListeners();
    }

    private void initFields() {
        ViewProfile_TXT_userName.setText (dataManager.getUsername ());
        ViewProfile_TXT_ProfileDesc.setText (dataManager.getUserDescription ());
        ViewProfile_TXT_PhoneNumber.setText (dataManager.getPhoneNumber ());
        ViewProfile_TXT_userEmail.setText (dataManager.getUserEmail ());
        ViewProfile_TXT_interestsTags.setText(createTextWithStringBuilder(dataManager.getUserTagsList ()));
    }

    /**
     * Function that creating text with String Builder
     * @param tagsList
     * @return
     */
    private String createTextWithStringBuilder(ArrayList<String> tagsList) {
        StringBuilder sb = new StringBuilder();
        for (Object item:tagsList){
            sb.append(item.toString() + "\n");
        }
        return sb.toString ();
    }



    private void getUserInstance() {
        bundle = getIntent ().getExtras ();
        String instanceBoundaryJson = bundle.getString (getString (R.string.BUNDLE_USER_INSTANCE_BOUNDARY_KEY));

        dataManager.setInstanceOfTypeUser (new Gson ().fromJson (instanceBoundaryJson, InstanceOfTypeUser.class));
    }

    private void getUserBoundary() {
        bundle = getIntent ().getExtras ();
        String userBoundaryJson = bundle.getString (getString (R.string.BUNDLE_USER_BOUNDARY_KEY));

        dataManager.setUserBoundary (new Gson ().fromJson (userBoundaryJson, UserBoundary.class));
    }

    private void setListeners() {
        ViewProfile_BTN_editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewProfileActivity.this, EditProfileActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

                //TODO change: do not finish this activity here, finish it in edit > when press APPLY btn
                finish();
            }
        });

        editProfile_IMG_Close.setOnClickListener(v->{
            Intent splashIntent = new Intent (this, MainPageActivity.class);
            startActivity (splashIntent);
        });
    }

    private void findViews() {
        ViewProfile_BTN_editBtn = findViewById(R.id.ViewProfile_BTN_editBtn);
        ViewProfile_IMG_logo = findViewById(R.id.ViewProfile_IMG_logo);
        ViewProfile_TXT_userEmail = findViewById(R.id.ViewProfile_TXT_userEmail);
        ViewProfile_TXT_userName = findViewById(R.id.ViewProfile_TXT_userName);
        ViewProfile_TXT_PhoneNumber = findViewById(R.id.ViewProfile_TXT_PhoneNumber);
        ViewProfile_TXT_ProfileDesc= findViewById(R.id.ViewProfile_TXT_ProfileDesc);
        ViewProfile_TXT_interestsTags= findViewById(R.id.ViewProfile_TXT_interestsTags);
        editProfile_IMG_Close = findViewById (R.id.editProfile_IMG_Close);

    }
}
