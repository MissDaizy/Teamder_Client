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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamder.R;
import com.example.teamder.databinding.FragmentNotificationsBinding;
import com.example.teamder.models.InstanceOfTypeGroup;
import com.example.teamder.ui.Group.GroupAdapter;

import java.util.List;

public class NotificationsFragment_pressedAddBTN extends Fragment {

    // For ViewAllMyTeamsActivity
    public static Fragment newInstance()  {

        return new NotificationsFragment_pressedAddBTN();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "here please add new group", Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.activity_create_group, container, false);
    }


}