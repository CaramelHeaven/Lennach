package com.caramelheaven.lennach.database;

import android.util.Log;

import com.caramelheaven.lennach.data.Board;
import com.caramelheaven.lennach.data.Thread;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Sergey F on 20.01.2018.
 */

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
     * @param pageSize Страницы подкачки
     * @param page     Первая страница
     */

    public void getFromDatabase(int pageSize, int page) {
        entityList.clear();

        RealmResults<BoardRealm> results = realm.where(BoardRealm.class).findAll();
        Log.i(LOGS, String.valueOf("Get From Database: " + entityList));
        int startPos = Math.max(results.size() - 1 - (page - 1) * pageSize, 0);
        int endPos = Math.max(startPos - pageSize, 0);

        for (int i = startPos; i > endPos; i--) {
            entityList.add(results.get(i).toEntity());
        }
    }
}
