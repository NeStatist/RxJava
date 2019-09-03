package com.example.rxjavafuck.network;

import com.example.rxjavafuck.model.Example;
import com.example.rxjavafuck.model.modelUsers.User;


import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {

    @GET("search_by_date")
    Single<Example> example (
            @Query("page") int page,
            @Query("tags") String tag
    );
    @GET("users/{username}")
    Single<User> user (
            @Path("username") String username
    );

}
