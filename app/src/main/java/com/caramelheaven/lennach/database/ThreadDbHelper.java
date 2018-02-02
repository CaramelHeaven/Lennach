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
        Log.i(LOGS, String.valueOf(entityList));
        this.entityList = entityList;
        this.realm = realm;
    }

    public void saveToDatabase() {
        Log.i(LOGS, String.valueOf("Save To Database: " + entityList));
        realm = null;
        try {
            realm = Realm.getDefaultInstance();
            if (entityList.size() > 0) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm r) {
                        for (int i = entityList.size() - 1; i >= 0; i--) {
                            ThreadRealm threadRealm = new ThreadRealm();
                            threadRealm.setFromEntityThread(entityList.get(i));
                            r.copyToRealmOrUpdate(threadRealm);
                        }
                    }
                });
            }
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public void getFromDatabase(int page, int pageSize) {
        entityList.clear();

        realm = Realm.getDefaultInstance();
        try {
            final RealmResults<ThreadRealm> results = realm.where(ThreadRealm.class).findAll();
            Log.i(LOGS, String.valueOf("Get From Database: " + entityList));
            Log.i(LOGS, String.valueOf(results));

            int startPos = Math.max(results.size() - 1 - (page - 1) * pageSize, 0);
            //Log.i(LOGS, "START_POS" + String.valueOf(startPos));
            //int endPos = results.size() - 1;
            int endPos = Math.max(startPos - pageSize, 0);
            //Log.i(LOGS, "ENG_POS" + String.valueOf(endPos));
            for (int i = startPos; i > endPos; i--) {
                try {
                    Log.d(LOGS, "GetFromDB for: " + i + " " + results.get(i).toEntityThread());
                    entityList.add(results.get(i).toEntityThread());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            realm.close();
        }
    }
}
