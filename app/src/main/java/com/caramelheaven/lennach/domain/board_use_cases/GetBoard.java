package com.caramelheaven.lennach.domain.board_use_cases;

import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.models.model.board_viewer.Board;

import io.reactivex.Single;

public class GetBoard {
    private final BoardRepository boardRepository;

    public GetBoard(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Single<Board> createUseCase() {
        return boardRepository.getBoard("b", 1);
    }
}
