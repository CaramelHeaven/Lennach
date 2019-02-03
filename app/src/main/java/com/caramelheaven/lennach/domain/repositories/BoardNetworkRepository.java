package com.caramelheaven.lennach.domain.repositories;

import com.caramelheaven.lennach.models.model.Board;

import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 16:36, 03/02/2019.
 */
public interface BoardNetworkRepository {
    Single<Board> getBoard(String boardName, int page);
}
