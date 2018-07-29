package com.caramelheaven.lennach.datasource.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.caramelheaven.lennach.datasource.database.dao.BoardDao;
import com.caramelheaven.lennach.datasource.database.dao.PostDao;
import com.caramelheaven.lennach.datasource.database.dao.ThreadDao;
import com.caramelheaven.lennach.datasource.database.entity.iBoard;
import com.caramelheaven.lennach.datasource.database.entity.iPost;
import com.caramelheaven.lennach.datasource.database.entity.iThread;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
@Database(entities = {iBoard.class, iThread.class, iPost.class}, version = 1, exportSchema = false)
public abstract class LennachDatabase extends RoomDatabase {
    public abstract BoardDao boardDao();

    public abstract ThreadDao threadDao();

    public abstract PostDao postDao();
}
