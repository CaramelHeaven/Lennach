package com.caramelheaven.lennach.di.application

import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by CaramelHeaven on 16:48, 03/02/2019.
 */
@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    internal fun provideContext(): Context {
        return context
    }
}
