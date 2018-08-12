package com.caramelheaven.lennach.datasource.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.caramelheaven.lennach.datasource.database.entity.iBoardNav;

import java.util.List;

@Dao
public interface BoardNavDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBoard(iBoardNav board);

    @Update
    void updateBoard(iBoardNav board);

    @Delete
    void deleteBoard(iBoardNav board);

    @Query("SELECT * FROM iBoardNav")
    List<iBoardNav> getBoards();
}
