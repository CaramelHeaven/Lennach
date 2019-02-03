package com.caramelheaven.lennach.data.datasource.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.caramelheaven.lennach.models.database.BoardDb;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 16:46, 03/02/2019.
 */
@Dao
public interface BoardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFavouriteBoard(BoardDb boardDb);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addListFavouriteBoards(List<BoardDb> boardDbList);

    @Query("SELECT * FROM BoardDb")
    Single<List<BoardDb>> getFavouritesBoards();
}