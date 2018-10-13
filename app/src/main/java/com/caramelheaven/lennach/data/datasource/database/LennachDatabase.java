package com.caramelheaven.lennach.data.datasource.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.caramelheaven.lennach.models.database.BoardEntity;
import com.caramelheaven.lennach.models.database.UsenetEntity;

@Database(entities = {BoardEntity.class, UsenetEntity.class}, version = 1, exportSchema = false)
public abstract class LennachDatabase extends RoomDatabase {
    public abstract BoardDao getBoardDao();

    public abstract ThreadDao getUsenetDao();
}
