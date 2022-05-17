package com.example.teamder.service;

import com.example.teamder.models.ActivityBoundary;
import com.example.teamder.models.InstanceOfTypeUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonApiActivities {
    @POST("activities")
    Call<Object> invokeActivity(@Body ActivityBoundary activityBoundary);
}
