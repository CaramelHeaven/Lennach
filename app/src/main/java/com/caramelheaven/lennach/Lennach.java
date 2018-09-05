package com.caramelheaven.lennach;

import android.app.Application;

import com.caramelheaven.lennach.datasource.di.AppComponent;
import com.caramelheaven.lennach.datasource.di.DaggerAppComponent;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
public class Lennach extends Application {

    private static AppComponent component;
    private static Lennach lennach;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        lennach = this;

        component = DaggerAppComponent.builder()
                .methodForSettingAppModule(lennach)
                .letsBuildThisComponent();
    }

    public static AppComponent getComponent() {
        return component;
    }
}