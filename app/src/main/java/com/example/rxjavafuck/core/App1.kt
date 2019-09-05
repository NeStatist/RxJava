package com.example.rxjavafuck.core

import android.app.Application

import com.example.rxjavafuck.network.ApiManager
import com.example.rxjavafuck.network.RestApi

class App : Application() {

    var api: RestApi? = null
        private set


    override fun onCreate() {
        super.onCreate()
        instanse = this
        api = ApiManager()
    }

    companion object {
        var instanse: App? = null
            private set
    }

}
