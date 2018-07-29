package com.caramelheaven.lennach.datasource.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.caramelheaven.lennach.datasource.database.entity.iBoard;

import java.util.List;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
@Dao
public interface BoardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBoard(iBoard board);

    @Update
    void updateBoard(iBoard board);

    @Delete
    void deleteBoard(iBoard board);

    @Query("SELECT * FROM iboard")
    List<iBoard> getBoards();
}
