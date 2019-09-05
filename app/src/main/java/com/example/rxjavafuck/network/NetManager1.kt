package com.example.rxjavafuck.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.IOException
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetManager {

    private val okHttpClient = OkHttpClient()
            .newBuilder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .addInterceptor(ResponseInterceptor()).build()

    private val retofit: Retrofit
        get() = Retrofit.Builder()
                .baseUrl("https://hn.algolia.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

    var restApi: RestApi? = null
        get() {
            if (field == null) {
                restApi = retofit.create(RestApi::class.java)
            }
            return field!!
        }

    internal inner class ResponseInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())

            val builder: Response.Builder

            builder = response.newBuilder()
                    .addHeader("SerialResponse-Type:", "application/x-www-form-urlencoded")

            return builder.build()
        }
    }
    private fun createGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create()
    }
    companion object {
        private var restAPI: RestApi? = null
        private var INSTANCE: NetManager? = null

        val instance: NetManager?
            get() {
                if (INSTANCE == null) {
                    synchronized(NetManager::class.java) {
                        if (INSTANCE == null) {
                            INSTANCE = NetManager()
                        }
                    }
                }
                return INSTANCE
            }
    }
}