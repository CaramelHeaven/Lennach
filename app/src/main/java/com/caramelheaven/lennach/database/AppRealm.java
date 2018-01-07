package com.caramelheaven.lennach.database;

import android.app.Application;

import com.caramelheaven.lennach.database.MyMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppRealm extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
