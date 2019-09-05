package com.example.rxjavafuck.network

import com.example.rxjavafuck.model.Example
import com.example.rxjavafuck.model.modelUsers.User

import io.reactivex.Single

class ApiManager : RestApi {
    private val restApi: RestApi = NetManager.instance?.restApi!!
    override fun example(page: Int, tag: String): Single<Example> {
        return restApi.example(page, tag)
    }

    override fun user(username: String): Single<User> {
        return restApi.user(username)
    }

}
