package com.caramelheaven.lennach.data.datasource.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.caramelheaven.lennach.models.database.BoardFavouriteDb;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 20:45, 23/01/2019.
 */
@Dao
public interface BoardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFavouriteBoard(BoardFavouriteDb boardFavouriteDb);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addListFavouriteBoards(List<BoardFavouriteDb> boardFavouriteDbs);

    @Query("SELECT * FROM BoardFavouriteDb")
    Single<List<BoardFavouriteDb>> getFavouritesBoards();
}
