package com.caramelheaven.lennach;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.caramelheaven.lennach.data.datasource.database.LennachDatabase;
import com.caramelheaven.lennach.di.ComponentsManager;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
public class Lennach extends Application {

    private static Lennach lennach;
    private static ComponentsManager componentsManager;
    private static LennachDatabase database;
    private static final String DATABASE_NAME = "lennach_db";

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        lennach = this;

        componentsManager = new ComponentsManager(lennach);
        componentsManager.getAppComponent();
    }

    public static Lennach getLennach() {
        return lennach;
    }

    public static ComponentsManager getComponentsManager() {
        return componentsManager;
    }

    public LennachDatabase getDatabase() {
        if (database == null) {
            database = Room.databaseBuilder(lennach, LennachDatabase.class, DATABASE_NAME)
                    .build();
        }
        return database;
    }
}
