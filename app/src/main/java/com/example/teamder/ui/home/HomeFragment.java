package com.example.teamder.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.teamder.databinding.FragmentHomeBinding;
import com.example.teamder.logic.DataManager;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private DataManager dataManager=new DataManager();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider (this).get (HomeViewModel.class);

        binding = FragmentHomeBinding.inflate (inflater, container, false);
        View root = binding.getRoot ();

        final TextView textView = binding.fragmentHomeTXTLogoTitle;


        homeViewModel.getText ().observe (getViewLifecycleOwner (), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView ();
        binding = null;
    }
}