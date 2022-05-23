package com.example.teamder.ui.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamder.R;
import com.example.teamder.databinding.FragmentNotificationsBinding;
import com.example.teamder.models.InstanceOfTypeGroup;
import com.example.teamder.ui.Group.GroupAdapter;

import java.util.List;

public class NotificationsFragment extends Fragment {

    // all the parameters that related to RecyclerView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View listItemsView;

    // values that will be send to the GroupAdapter
    private Context context;
    private List<InstanceOfTypeGroup> allGroups; //here set all the values from DB..

    // for main page & notification
    private FragmentNotificationsBinding binding;

    // for ViewAllMyTeamsActivity
    public static Fragment newInstance() {
        return new NotificationsFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //inflate the fragment_notifications to the view group
        //the second line uses the listOfGroups to get the recycleview
        listItemsView = inflater.inflate(R.layout.fragment_notifications, container, false);
        findViews(listItemsView);

        //set Linear Layout Manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //set adapter: this actually loads the Groups to the recyclerview
        recyclerAdapter = new GroupAdapter(allGroups);
        recyclerView.setAdapter(recyclerAdapter);


        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider (this).get (NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate (inflater, container, false);
        View root = binding.getRoot ();

//        final TextView textView = binding.textNotifications;
//
//        notificationsViewModel.getText ().observe (getViewLifecycleOwner (), textView::setText);
        return listItemsView;
    }

    private void findViews(View view) {
        recyclerView = view.findViewById(R.id.listOfGroups);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView ();
        binding = null;
    }
}