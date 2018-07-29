package com.caramelheaven.lennach.datasource.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.caramelheaven.lennach.datasource.database.LennachDatabase;
import com.caramelheaven.lennach.datasource.network.ApiService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient.Builder builder) {
        OkHttpClient client = builder.build();
        return new Retrofit.Builder()
                .baseUrl("https://2ch.hk/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    public ApiService getApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                return chain.proceed(request);
            }
        });
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder;
    }

    @Provides
    @Singleton
    public LennachDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application, LennachDatabase.class, "lennach.db")
                .build();
    }
}
