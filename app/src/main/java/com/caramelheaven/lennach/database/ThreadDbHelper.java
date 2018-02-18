package com.caramelheaven.lennach.database;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ThreadDbHelper {
    private RealmList<ThreadRealm> entityList;
    private Realm realm;
    private static final String LOGS = ThreadDbHelper.class.getSimpleName();

    public ThreadDbHelper(RealmList<ThreadRealm> entityList, Realm realm) {
        Log.d(LOGS, String.valueOf("Entity List in constructor: " + entityList));
        this.entityList = entityList;
        this.realm = realm;
    }

    public void saveToDatabase() {
        Log.d(LOGS, String.valueOf("Save To Database: " + entityList));
        realm = null;
        try {
            realm = Realm.getDefaultInstance();
            if (entityList.size() > 0) {
                realm.executeTransaction(r -> {
                    Log.d(LOGS, "SIze Entity list: " + entityList.size());
                    for (int i = 0; i < entityList.size(); i++) {
                        ThreadRealm threadRealm = new ThreadRealm();
                        threadRealm.setFromEntityThread(entityList.get(i));
                        Log.d(LOGS, "entityList.get(i): " + entityList.get(i));
                        Log.d(LOGS, "File: " + entityList.get(i).getFiles());
                        Log.d(LOGS, "Comment: " + entityList.get(i).getComment());
                        Log.d(LOGS, "Date: " + entityList.get(i).getDate());
                        r.copyToRealmOrUpdate(threadRealm);
                    }
                });
                Log.d(LOGS, "Check FindAll: " + String.valueOf(realm.where(ThreadRealm.class).findAll().size()));
            }
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public void getFromDatabase(int page, int pageSize) {
        realm = Realm.getDefaultInstance();
        Log.d(LOGS, "PAge: " + page);
        Log.d(LOGS, "PageSize: " + pageSize);
        try {
            final RealmResults<ThreadRealm> results = realm.where(ThreadRealm.class).findAll();
            Log.d(LOGS, String.valueOf("Result SIZE: " + results.size()));
            //Log.i(LOGS, "START_POS" + String.valueOf(startPos));
            //int endPos = results.size() - 1;
            //Log.i(LOGS, "ENG_POS" + String.valueOf(endPos));

            for (int i = 0; i < results.size(); i++) {
                try {
                    Log.d(LOGS, "GetFromDB for: " + i + " " + results.get(i).toEntityThread());
                    entityList.add(results.get(i).toEntityThread());
                    Log.d(LOGS, String.valueOf("for (int i = startPos; i > endPos; i--) {: " + entityList));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            realm.close();
        }
    }
}
