package com.example.teamder.service;

import com.example.teamder.models.NewUserBoundary;
import com.example.teamder.models.UserBoundary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonApiUsers {
    /*
    Input: New User Boundary (From this client)
    Output: User Boundary (From server)
     */
  @POST("users")
    Call<UserBoundary> createUser(@Body NewUserBoundary user);

    @PUT("users/{userDomain}/{userEmail}")
    Call<Void> updateUser(@Path(value="userDomain") String userDomain,@Path(value="userEmail") String userEmail,
                                                         @Body UserBoundary userBoundary);

    @GET("users/login/{userDomain}/{userEmail}")
    Call<UserBoundary> getUserBoundary(@Path(value="userDomain") String userDomain , @Path(value="userEmail") String userEmail);
}

