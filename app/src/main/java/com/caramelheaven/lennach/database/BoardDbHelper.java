package com.caramelheaven.lennach.database;

import android.util.Log;

import com.caramelheaven.lennach.data.Board;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class BoardDbHelper {
    private RealmList<BoardRealm> entityList;
    private Realm realm;
    private final static String LOGS = BoardDbHelper.class.getSimpleName();

    public BoardDbHelper(RealmList<BoardRealm> entityList, Realm realm) {
        Log.i(LOGS, String.valueOf(entityList));
        this.entityList = entityList;
        this.realm = realm;
    }

    //Save to DB, used in getData() Observable
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
                            BoardRealm boardRealm = new BoardRealm();
                            boardRealm.setFromEntity(entityList.get(i));
                            r.copyToRealmOrUpdate(boardRealm);
                        }
                    }
                });
                Log.d(LOGS, "Check FindAll" + String.valueOf(realm.where(BoardRealm.class).findAll().size()));
            }
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
            /*realm.beginTransaction();
            BoardRealm boardRealm = new BoardRealm();
            boardRealm.setFromEntity(entityList.get(i));
            realm.commitTransaction();*/
    }


    /**
     * @param pageSize size
     * @param page     first page
     */

    public RealmList<BoardRealm> getFromDatabase(int page, int pageSize) {
        entityList.clear();
        /*
        Example - We have a startPos - 136 and endPos - 96, sum = 40. 40 threads-data we can view.
        Why 136? If we set 0 to startPos, and result.size()-1 to endPos -> we get old data
        in start - and a fresh data - in the end.
        */
        realm = Realm.getDefaultInstance();
        try {
            final RealmResults<BoardRealm> results = realm.where(BoardRealm.class).findAll();
            Log.d(LOGS, "EntityList - " + String.valueOf("Get From Database: " + entityList));
            Log.d(LOGS, "RealmResult Board: " + String.valueOf(results));
            //int startPos = results.size() - results.size();
            int startPos = Math.max(results.size() - 1 - (page - 1) * pageSize, 0);
            Log.d(LOGS, "START_POS" + String.valueOf(startPos));
            //int endPos = results.size() - 1;
            int endPos = Math.max(startPos - pageSize, 0);
            Log.d(LOGS, "ENG_POS" + String.valueOf(endPos));
            for (int i = startPos; i > endPos; i--) {
                try {
                    Log.d(LOGS, "GetFromDB for: " + i + " " + results.get(i).toEntity());
                    entityList.add(results.get(i).toEntity());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            realm.close();
        }
        return entityList;
    }
}
