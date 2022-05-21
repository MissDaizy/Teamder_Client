package com.example.teamder.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    public String username;
    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<> ();
        //mText.setValue ("This is home fragment");
        getData();
    }

    void getData() {


    }

    public LiveData<String> getText() {
        return mText;
    }
}