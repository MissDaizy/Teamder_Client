package com.example.teamder.server;

import com.example.teamder.models.InstanceBoundary;
import com.example.teamder.models.NewUserBoundary;
import com.example.teamder.models.UserBoundary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderInstance {
    @POST("instances")
    Call<InstanceBoundary> createInstance(@Body InstanceBoundary instanceBoundary);
}
