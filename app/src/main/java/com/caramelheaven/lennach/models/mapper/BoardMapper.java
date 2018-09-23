package com.caramelheaven.lennach.models.mapper;

import com.caramelheaven.lennach.models.model.board_viewer.Board;
import com.caramelheaven.lennach.models.network.BoardResponse;

public class BoardMapper {
    private BoardResponseToBoard boardResponseToBoard;
    private BoardEntityToBoard boardEntityToBoard;

    public BoardMapper(BoardResponseToBoard boardResponseToBoard,
                       BoardEntityToBoard boardEntityToBoard) {
        this.boardResponseToBoard = boardResponseToBoard;
        this.boardEntityToBoard = boardEntityToBoard;
    }

    public Board map(BoardResponse boardResponse) {
        return boardResponseToBoard.map(boardResponse);
    }
}
