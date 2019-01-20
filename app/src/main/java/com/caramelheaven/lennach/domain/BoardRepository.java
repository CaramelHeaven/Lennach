package com.caramelheaven.lennach.domain;

import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.models.model.board.BoardAll;

import java.util.List;

import io.reactivex.Single;

public interface BoardRepository {
    Single<Board> getBoard(String boardName, int page);

    Single<List<BoardAll>> getAllBoards();
}
