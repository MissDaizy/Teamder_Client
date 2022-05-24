package com.example.teamder.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamder.R;
import com.example.teamder.databinding.FragmentNotificationsBinding;
import com.example.teamder.models.InstanceOfTypeGroup;
import com.example.teamder.ui.Group.GroupAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class NotificationsFragment extends Fragment implements GroupAdapter.OnNoteListener {

    // Related to RecyclerView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View listItemsView;

    // Will be send to the GroupAdapter
    private Context context;
    private List<InstanceOfTypeGroup> allGroups; //here set all the values from DB..

    // For main page & notification
    private FragmentNotificationsBinding binding;

    // For add new group
    private MaterialButton groups_addGroup;

    // For ViewAllMyTeamsActivity
    public static Fragment newInstance() {
        return new NotificationsFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "here you get all your groups", Toast.LENGTH_SHORT).show();


        //inflate the fragment_notifications to the view group
        listItemsView = inflater.inflate(R.layout.fragment_notifications, container, false);
        findViews(listItemsView);
        setRecyclerView();
        androidCodeForBottomNavigationBar(inflater,container);
        setListeners();


//TODO - delete before submission
//        final TextView textView = binding.textNotifications;
//        notificationsViewModel.getText ().observe (getViewLifecycleOwner (), textView::setText);
        return listItemsView;
    }

    private void setListeners() {

        // Activate the layout activity_create_group with fragment: NotificationsFragment_pressedAddBTN
        groups_addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment addNewProj = new NotificationsFragment_pressedAddBTN();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, addNewProj);

                //TODO: option go back to the "Group" first frag
                fragmentTransaction.addToBackStack("NotificationsFragment");

                fragmentTransaction.commit();

            }
        });
    }

    private void androidCodeForBottomNavigationBar(@NonNull LayoutInflater inflater,
                                                   ViewGroup container) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider (this).get (NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate (inflater, container, false);
        View root = binding.getRoot ();
    }

    private void setRecyclerView() {
        //set Linear Layout Manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //set adapter: this actually loads the Groups to the recyclerview
        recyclerAdapter = new GroupAdapter(allGroups, this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void findViews(View view) {
        // "Make" listOfGroups to get the recyclerview
        recyclerView = view.findViewById(R.id.listOfGroups);
        groups_addGroup = view.findViewById(R.id.groups_addGroup);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView ();
        binding = null;
    }

    // Activate view chosen group
    @Override
    public void onNoteClick(int position) {

//TODO DIANCHIK's:        allGroups.get(position);

        Fragment addNewProj = new NotificationsFragment_viewChosen();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, addNewProj);

        //TODO: option go back to the "Group" first frag
        fragmentTransaction.addToBackStack("NotificationsFragment");

        fragmentTransaction.commit();

//        Intent intent = new Intent(this, ViewChosenGroupActivity.class);
//        startActivity(intent);
    }
}