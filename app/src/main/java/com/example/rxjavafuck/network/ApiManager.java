package com.example.rxjavafuck.network;

import com.example.rxjavafuck.model.Example;
import com.example.rxjavafuck.model.modelUsers.User;

import io.reactivex.Single;

public class ApiManager implements RestApi {

    private RestApi restApi;

    public ApiManager() {
        restApi = NetManager.getInstance().getRestApi();
    }

    @Override
    public Single<Example> example(int page, String tag) {
        return restApi.example(page,tag);
    }
    @Override
    public Single<User> user(String username) {
        return restApi.user(username);
    }
}
