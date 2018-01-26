package com.caramelheaven.lennach.database;

import android.util.Log;

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
        if (entityList.size() > 0) {
            for (int i = entityList.size() - 1; i >= 0; i--) {
                realm.beginTransaction();
                BoardRealm boardRealm = new BoardRealm();
                boardRealm.setFromEntity(entityList.get(i));
                realm.copyToRealmOrUpdate(boardRealm);
                realm.commitTransaction();
            }
        }
    }

    /**
     * @param pageSize size
     * @param page     first page
     */

    public void getFromDatabase(int page, int pageSize) {
        entityList.clear();

        RealmResults<BoardRealm> results = realm.where(BoardRealm.class).findAll();
        /*
        Example - We have a startPos - 136 and endPos - 96, sum = 40. 40 threads-data we can view.
        Why 136? If we set 0 to startPos, and result.size()-1 to endPos -> we get old data
        in start - and a fresh data - in the end.
        */
        Log.i(LOGS, String.valueOf("Get From Database: " + entityList));
        Log.i(LOGS, String.valueOf(results));
        //int startPos = results.size() - results.size();
        int startPos = Math.max(results.size() - 1 - (page - 1) * pageSize, 0);
        //Log.i(LOGS, "START_POS" + String.valueOf(startPos));
        //int endPos = results.size() - 1;
        int endPos = Math.max(startPos - pageSize, 0);
        //Log.i(LOGS, "ENG_POS" + String.valueOf(endPos));

        for (int i = startPos; i > endPos; i--) {
            try {
                Log.d(LOGS, "GetFromDB for: " + i + " " + results.get(i).toEntity());
                entityList.add(results.get(i).toEntity());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
