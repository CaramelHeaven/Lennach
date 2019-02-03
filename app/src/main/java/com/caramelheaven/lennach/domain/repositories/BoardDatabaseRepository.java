package com.caramelheaven.lennach.domain.repositories;

import com.caramelheaven.lennach.models.database.BoardDb;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 16:42, 03/02/2019.
 */
public interface BoardDatabaseRepository {
    Completable saveFavouriteBoards(List<BoardDb> values);

    Single<List<BoardDb>> getFavouriteBoards();
}
