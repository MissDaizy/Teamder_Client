package com.example.teamder.ui.notifications;

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
import com.example.teamder.callbacks.CallBack_ListGroupClicked;
import com.example.teamder.databinding.FragmentNotificationsBinding;
import com.example.teamder.models.InstanceOfTypeGroup;
import com.example.teamder.ui.Group.GroupAdapter;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private CallBack_ListGroupClicked callBack_groupClicked;
    private ArrayList<InstanceOfTypeGroup> allGroups = new ArrayList<>();
    private FragmentNotificationsBinding binding;

    public NotificationsFragment() {
//        this.callBack_groupClicked = CallBack_groupClicked;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        findViews(view);
        setRecyclerView(view);

        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider (this).get (NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate (inflater, container, false);
        View root = binding.getRoot ();

//        final TextView textView = binding.textNotifications;
//
//        notificationsViewModel.getText ().observe (getViewLifecycleOwner (), textView::setText);
        return root;
    }

    private void setRecyclerView(View view) {
        GroupAdapter adapter = new GroupAdapter(view.getContext(), allGroups);
//        allGroups_LST_items.setAdapter(adapter);
//        allGroups_LST_items.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


    }

    private void findViews(View view) {
        recyclerView = view.findViewById(R.id.groupsAll_LST_items);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView ();
        binding = null;
    }
}