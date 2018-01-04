/*
package ru.caramelheaven.lennach;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.caramelheaven.lennach.network.DvachService;

*/
/**
 * Created by Sergey F on 02.01.2018.
 *//*


public class ApiFactory {
    private static OkHttpClient sClient;

    private static DvachService sService;

    @NonNull
    public static DvachService getMoviesService() {
        DvachService service = sService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sService;
                if (service == null) {
                    service = sService = createService();
                }
            }
        }
        return service;
    }

    @NonNull
    private static DvachService createService() {
        return new Retrofit.Builder()
                .baseUrl("https://2ch.hk")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DvachService.class);
    }
}

*/
