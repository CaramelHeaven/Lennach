package com.caramelheaven.lennach.domain;

import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.models.model.board.BoardFavourite;

import java.util.List;

import io.reactivex.Single;

public interface BoardRepository {
    Single<Board> getBoard(String boardName, int page);

    Single<List<BoardFavourite>> getAllBoards();
}
