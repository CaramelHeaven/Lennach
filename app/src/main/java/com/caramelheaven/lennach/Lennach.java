package com.caramelheaven.lennach;

import android.app.Application;

import com.caramelheaven.lennach.di.ComponentsManager;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
public class Lennach extends Application {

    private static  ComponentsManager componentsManager;

    @Override
    public void onCreate() {
        super.onCreate();

        componentsManager = new ComponentsManager(this);
        //componentsManager.getAppComponent();
    }

    public static ComponentsManager getComponentsManager() {
        return componentsManager;
    }
}
