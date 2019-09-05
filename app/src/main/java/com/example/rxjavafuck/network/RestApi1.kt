package com.example.rxjavafuck.network

import com.example.rxjavafuck.model.Example
import com.example.rxjavafuck.model.modelUsers.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET("search_by_date")
    fun example(
            @Query("page") page: Int,
            @Query("tags") tag: String
    ): Single<Example>

    @GET("users/{username}")
    fun user(
            @Path("username") username: String
    ): Single<User>

}
