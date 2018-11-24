package com.caramelheaven.lennach.domain;

import com.caramelheaven.lennach.models.model.board.Board;

import io.reactivex.Single;

public interface BoardRepository {
    Single<Board> getBoard(String boardName, int page);
}
