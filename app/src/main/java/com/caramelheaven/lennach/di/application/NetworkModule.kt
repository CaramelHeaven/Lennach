package com.caramelheaven.lennach.di.application

import com.caramelheaven.lennach.data.datasource.network.LennachApiService
import com.caramelheaven.lennach.utils.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import java.util.concurrent.TimeUnit

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by CaramelHeaven on 16:48, 03/02/2019.
 */
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(builder: OkHttpClient.Builder): Retrofit {
        val client = builder.build()
        return Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): LennachApiService {
        return retrofit.create(LennachApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.addInterceptor { chain ->
            val request = chain.request()
            chain.proceed(request)
        }
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        return builder
    }
}
