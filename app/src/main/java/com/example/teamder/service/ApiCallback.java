package com.example.teamder.service;

import com.example.teamder.models.UserBoundary;

public interface ApiCallback {
    void onSuccess(UserBoundary userBoundary);
}
