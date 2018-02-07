package com.caramelheaven.lennach.database;

import android.app.Application;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppRealm extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfig);

        Realm.getDefaultInstance().executeTransaction(realm -> {
            Toast.makeText(AppRealm.this, "Deleted Realm database", Toast.LENGTH_SHORT).show();
            realm.deleteAll();
        });
    }
}
