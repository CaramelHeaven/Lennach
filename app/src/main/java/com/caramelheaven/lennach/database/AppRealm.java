package com.caramelheaven.lennach.database;

import android.app.Application;
import android.widget.Toast;

import com.caramelheaven.lennach.database.MyMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppRealm extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("lennach.realm")
                .schemaVersion(3)
                .migration(new MyMigration())
                .build();

        Realm.setDefaultConfiguration(realmConfig);

        /*Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Toast.makeText(AppRealm.this, "Deleted Realm database", Toast.LENGTH_SHORT).show();
                realm.deleteAll();
            }
        });*/
    }
}
