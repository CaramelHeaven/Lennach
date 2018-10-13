package com.caramelheaven.lennach.data.datasource.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.caramelheaven.lennach.models.database.UsenetEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ThreadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertThread(UsenetEntity entity);

    @Query("SELECT * FROM UsenetTable")
    Single<List<UsenetEntity>> getSavedUsenets();
}
