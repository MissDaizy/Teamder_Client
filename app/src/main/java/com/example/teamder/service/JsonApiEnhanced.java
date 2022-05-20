package com.example.teamder.service;

import com.example.teamder.models.InstanceBoundary;
import com.example.teamder.models.InstanceOfTypeUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonApiEnhanced {
    /*
    In spring:
    		// Search instances by name - new method for Spring 5
				@RequestMapping(path = "/iob/instances/search/byName/{name}",
						method = RequestMethod.GET,
						produces = MediaType.APPLICATION_JSON_VALUE)
				public InstanceBoundary[] searchInstanceByName(@PathVariable("name") String name,
																	@RequestParam(name="userDomain", required = true) String userDomain,
																	@RequestParam(name="userEmail", required = true) String userEmail,
																	@RequestParam(name="size", required = false, defaultValue = "10") int size,
																	@RequestParam(name="page", required = false, defaultValue = "0") int page) {

					return this.instancesService.getInstancesName(userDomain,userEmail,page,size,name).toArray(new InstanceBoundary[0]);
				}
     */
    @GET("instances/search/byName/{name}")
    Call<List<InstanceOfTypeUser>> getInstanceByName(@Path(value="name") String name,
                                                     @Query (value="userDomain") String userDomain,
                                                     @Query (value = "userEmail") String userEmail,
                                                     @Query (value = "size") int size,
                                                     @Query (value = "page") int page);

}
