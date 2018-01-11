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

        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("lennach1.realm")
                .schemaVersion(1)
                .migration(new MyMigration())
                .build();

        Realm.deleteRealm(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);
    }
}
