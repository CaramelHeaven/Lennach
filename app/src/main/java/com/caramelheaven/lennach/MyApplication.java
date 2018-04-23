package com.caramelheaven.lennach;

import android.app.Application;
import android.content.Context;

import com.caramelheaven.lennach.datasourse.network.MainApiInterface;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private static MyApplication myApplication;
    private static OkHttpClient okHttpClient;

    private MainApiInterface mainApiInterface;

    public static MyApplication getInstance() {
        return myApplication;
    }

    public static Context getContext() {
        return myApplication.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        myApplication = this;

        OkHttpClient.Builder builderWithoutAuth = initBuilderWithoutAuth();
        OkHttpClient client = builderWithoutAuth.build();

        Retrofit retrofitJSONAPI = new Retrofit.Builder()
                .baseUrl(getBaseAddress())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        mainApiInterface = retrofitJSONAPI.create(MainApiInterface.class);
    }

    private OkHttpClient.Builder initBuilderWithoutAuth() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(5, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return builder;
    }


    public String getBaseAddress() {
        return "https://2ch.hk";
    }

    public MainApiInterface getMainApiInterface() {
        return mainApiInterface;
    }
}
