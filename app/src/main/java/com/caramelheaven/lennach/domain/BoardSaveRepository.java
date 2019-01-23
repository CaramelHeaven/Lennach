package com.caramelheaven.lennach.domain;

import com.caramelheaven.lennach.models.model.board.BoardFavourite;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 20:59, 23/01/2019.
 */
public interface BoardSaveRepository {
    Completable saveFavouriteBoards(List<BoardFavourite> values);

    Single<List<BoardFavourite>> getFavouriteBoards();
}
