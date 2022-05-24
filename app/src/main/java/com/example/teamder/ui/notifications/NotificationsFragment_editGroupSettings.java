package com.example.teamder.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.teamder.R;

public class NotificationsFragment_editGroupSettings extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "edit group execute", Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.activity_edit_group, container, false);
    }
}
