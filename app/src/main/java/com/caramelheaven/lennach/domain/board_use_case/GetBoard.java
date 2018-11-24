package com.caramelheaven.lennach.domain.board_use_case;

import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.models.model.board.Board;

import io.reactivex.Single;

public class GetBoard {
    private final BoardRepository repository;
    private int pageIndex = 1;

    public GetBoard(BoardRepository repository) {
        this.repository = repository;
    }

    public Single<Board> subscribeToData(String boardName) {
        return repository.getBoard(boardName, pageIndex);
    }

    public void setPage(int page) {
        this.pageIndex = page;
    }
}
