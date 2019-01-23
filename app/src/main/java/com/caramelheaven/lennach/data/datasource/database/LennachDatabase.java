package com.caramelheaven.lennach.data.datasource.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.caramelheaven.lennach.data.datasource.database.dao.BoardDao;
import com.caramelheaven.lennach.models.database.BoardFavouriteDb;

/**
 * Created by CaramelHeaven on 20:43, 23/01/2019.
 */
@Database(entities = {BoardFavouriteDb.class}, version = 1, exportSchema = false)
public abstract class LennachDatabase extends RoomDatabase {
    public abstract BoardDao getBoardDao();
}
