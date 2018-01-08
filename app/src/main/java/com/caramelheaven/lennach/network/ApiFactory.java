package com.caramelheaven.lennach.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private static ApiService apiService;
    private static OkHttpClient okHttpClient;

    public static ApiService getCheckingService() {
        ApiService service = apiService;
        if (service == null) {
            synchronized (ApiService.class) {
                service = apiService;
                if (service == null) {
                    service = apiService = createService();
                }
            }
        }
        return service;
    }

    private static ApiService createService() {
        return new Retrofit.Builder()
                .baseUrl("https://2ch.hk")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class);
    }

    public static OkHttpClient getClient() {
        OkHttpClient client = okHttpClient;
        if (client == null) {
            synchronized (ApiFactory.class) {
                client = okHttpClient;
                if (client == null) {
                    client = okHttpClient = buildClient();
                }
            }
        }
        return client;
    }

    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor())
                .build();
    }
}
