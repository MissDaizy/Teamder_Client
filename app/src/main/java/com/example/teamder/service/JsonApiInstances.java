package com.example.teamder.service;

import com.example.teamder.models.InstanceBoundary;
import com.example.teamder.models.InstanceOfTypeGroup;
import com.example.teamder.models.InstanceOfTypeUser;
import com.example.teamder.models.UserBoundary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonApiInstances {
    @POST("instances")
    Call<InstanceOfTypeUser> createInstanceUser(@Body InstanceOfTypeUser instanceBoundary);

    @POST("instances")
    Call<InstanceOfTypeGroup> createInstanceGroup(@Body InstanceOfTypeGroup instanceBoundary);

    @PUT("instances/{instanceDomain}/{instanceId}")
    Call<Void> updateInstanceTypeUser(@Path(value="instanceDomain") String instanceDomain, @Path(value="instanceId") String instanceId,
                                                @Query (value="userDomain") String userDomain, @Query (value="userEmail") String userEmail,
                                                @Body InstanceOfTypeUser instanceOfTypeUser);

    @PUT("instances/{instanceDomain}/{instanceId}")
    Call<Void> updateInstanceTypeGroup(@Path(value="instanceDomain") String instanceDomain, @Path(value="instanceId") String instanceId,
                                      @Query (value="userDomain") String userDomain, @Query (value="userEmail") String userEmail,
                                      @Body InstanceOfTypeGroup instanceOfTypeGroup);

    @GET("instances/{instanceDomain}/{instanceId}")
    Call<InstanceOfTypeUser> getInstanceTypeUser(@Path(value="instanceDomain") String instanceDomain, @Path(value="instanceId") String instanceId);

    @GET("instances/{instanceDomain}/{instanceId}")
    Call<InstanceOfTypeGroup> getInstanceTypeGroup(@Path(value="instanceDomain") String instanceDomain, @Path(value="instanceId") String instanceId);

    @GET("instances")
    Call<List<InstanceBoundary>> getAllInstances();


//TODO: ADD ARRAYLIST OF PHOTOS IN ATTRIBUTES OF GROUPS
}
