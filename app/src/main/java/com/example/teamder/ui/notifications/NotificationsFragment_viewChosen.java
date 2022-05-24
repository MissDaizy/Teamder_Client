package com.example.teamder.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.teamder.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class NotificationsFragment_viewChosen extends Fragment {

    private View chosenView;

    private MaterialButton viewGroup_BTN_editBTN;
    private MaterialButton viewGroup_BTN_deleteBTN;
    private MaterialButton viewGroup_BTN_exitView;

    // Set group data from DB
    private ImageView viewGroup_IMG_GroupImage;
    private MaterialTextView createProfileDesc_TXT_TeamName;
    private MaterialTextView viewGroup_TXT_topic;
    private MaterialTextView viewGroup_TXT_tags;
    private TextView viewGroup_TXT_maxNumOfMembersInTeam;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "view the group you chose", Toast.LENGTH_SHORT).show();

        chosenView = inflater.inflate(R.layout.fragment_view_choosen_team, container, false);
        findViews(chosenView);
        setListeners();

        return chosenView;
    }

    private void setListeners() {
        // Activate the layout activity_create_group with fragment: NotificationsFragment_pressedAddBTN
        viewGroup_BTN_editBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment editGroupSettings = new NotificationsFragment_editGroupSettings();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, editGroupSettings);

                //TODO: option go back to the "Group" first frag
                fragmentTransaction.addToBackStack("NotificationsFragment");

                fragmentTransaction.commit();

            }
        });

        viewGroup_BTN_exitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment backToGroups = new NotificationsFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, backToGroups);

                fragmentTransaction.commit();

            }
        });

        viewGroup_BTN_deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: (DIANCHIK's)
                // 1) delete this team group from DB,
                // 2) update the view

            }
        });
    }

    private void findViews(View view) {
        viewGroup_BTN_editBTN = view.findViewById(R.id.viewGroup_BTN_editBTN);
        viewGroup_BTN_deleteBTN = view.findViewById(R.id.viewGroup_BTN_deleteBTN);
        viewGroup_BTN_exitView = view.findViewById(R.id.viewGroup_BTN_exitView);

        //for "show" the group
        viewGroup_IMG_GroupImage = view.findViewById(R.id.viewGroup_IMG_GroupImage);
        createProfileDesc_TXT_TeamName = view.findViewById(R.id.createProfileDesc_TXT_TeamName);
        viewGroup_TXT_topic = view.findViewById(R.id.viewGroup_TXT_topic);
        viewGroup_TXT_tags = view.findViewById(R.id.viewGroup_TXT_tags);
        viewGroup_TXT_maxNumOfMembersInTeam = view.findViewById(R.id.viewGroup_TXT_maxNumOfMembersInTeam);
    }

}
