package com.example.teamder.service;

import com.example.teamder.models.ActivityBoundary;
import com.example.teamder.models.InstanceBoundary;
import com.example.teamder.models.InstanceOfTypeUser;
import com.example.teamder.models.UserBoundary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonApiAdmin {
    @DELETE("admin/users")
    Call<Void> deleteAllUsers();

    @DELETE("admin/instances")
    Call<Void> deleteAllInstances();

    @DELETE("admin/activities")
    Call<Void> deleteAllActivities();

    @GET("admin/users")
    Call<List<UserBoundary>> exportAllUsers();

    @GET("admin/activities")
    Call<List<ActivityBoundary>> exportAllActivities();
}
