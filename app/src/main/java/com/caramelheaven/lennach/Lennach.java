package com.caramelheaven.lennach;

import android.app.Application;

import com.caramelheaven.lennach.di.ComponentsManager;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
public class Lennach extends Application {

    private static Lennach lennach;
    private static ComponentsManager componentsManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        lennach = this;

        componentsManager = new ComponentsManager();
        componentsManager.getAppComponent();
    }

    public static ComponentsManager getComponentsManager() {
        return componentsManager;
    }
}
