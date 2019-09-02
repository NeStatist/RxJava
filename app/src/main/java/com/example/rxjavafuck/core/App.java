package com.example.rxjavafuck.core;

import android.app.Application;

import com.example.rxjavafuck.network.ApiManager;
import com.example.rxjavafuck.network.RestApi;

public class App extends Application {

    private RestApi restApi;
    private static App instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        restApi = new ApiManager();
    }

    public RestApi getApi() {
        return restApi;
    }

    public static App getInstanse() {
        return instance;
    }

}
