package com.example.teamder.logic;

import com.example.teamder.models.NewUserBoundary;
import com.example.teamder.models.UserBoundary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    @GET("users/login/{userDomain}/{userEmail}")
    Call<UserBoundary> getUserBoundary(@Path(value="userDomain") String userDomain , @Path(value="userEmail") String userEmail);

    /*
    Input: New User Boundary (From this client)
    Output: User Boundary (From server)
     */
  @POST("users")
    Call<UserBoundary> createUser(@Body NewUserBoundary user);
}
