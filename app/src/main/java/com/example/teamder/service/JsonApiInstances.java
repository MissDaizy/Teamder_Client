package com.example.teamder.service;

import com.example.teamder.models.InstanceBoundary;
import com.example.teamder.models.InstanceOfTypeUser;
import com.example.teamder.models.UserBoundary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonApiInstances {
    @POST("instances")
    Call<InstanceOfTypeUser> createInstance(@Body InstanceOfTypeUser instanceBoundary);

    @PUT("instances/{instanceDomain}/{instanceId}")
    Call<Void> updateInstance(@Path(value="instanceDomain") String instanceDomain, @Path(value="instanceId") String instanceId,
                                                @Body InstanceBoundary instanceBoundary);

    @GET("instances/{instanceDomain}/{instanceId}")
    Call<InstanceBoundary> getInstance(@Path(value="instanceDomain") String instanceDomain, @Path(value="instanceId") String instanceId);

    @GET("instances")
    Call<List<InstanceBoundary>> getAllInstances();


//TODO: ADD ARRAYLIST OF PHOTOS IN ATTRIBUTES OF GROUPS
}
