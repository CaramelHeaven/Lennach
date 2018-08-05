package com.caramelheaven.lennach.datasource.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.caramelheaven.lennach.datasource.database.entity.helpers.PostsInThreads;
import com.caramelheaven.lennach.datasource.database.entity.iThread;

import java.util.List;

/**
 * Created by CaramelHeaven on 29.07.2018
 */
@Dao
public interface ThreadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertThread(iThread iThread);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertThreads(List<iThread> iThreads);

    @Update
    void updateThread(iThread iThread);

    @Delete
    void deleteThread(iThread iThread);

    @Query("SELECT * FROM iThread")
    List<iThread> getAllThreads();

    @Query("SELECT * FROM iThread WHERE idBoard=:boardId")
    List<iThread> findThreadsByBoard(final int boardId);

    @Transaction
    @Query("SELECT * FROM iThread WHERE threadId=:threadId")
    PostsInThreads getPosts(String threadId);
}
