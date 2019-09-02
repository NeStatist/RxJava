package com.example.rxjavafuck.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManager {
    private static RestApi restAPI;
    private static NetManager INSTANCE;

    public static NetManager getInstance() {
        if (INSTANCE == null) {
            synchronized ((NetManager.class)) {
                if (INSTANCE == null) {
                    INSTANCE = new NetManager();
                }
            }
        }
        return INSTANCE;
    }

    private OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .addInterceptor(new ResponseInterceptor()).build();

    private Retrofit getRetofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hn.algolia.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    class ResponseInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());

            Response.Builder builder;

            builder = response.newBuilder()
                    .addHeader("SerialResponse-Type:", "application/x-www-form-urlencoded");

            return builder.build();
        }
    }

    public final RestApi getRestApi() {
        if (restAPI == null)
            restAPI = getRetofit().create(RestApi.class);
        return restAPI;
    }

    private Gson createGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create();
    }
}