package com.caramelheaven.lennach.models.mapper.board;

import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.models.network.BoardResponse;

public class BoardMapper {
    private BoardResponseToBoard boardResponseToBoard;

    public BoardMapper(BoardResponseToBoard boardResponseToBoard) {
        this.boardResponseToBoard = boardResponseToBoard;
    }

    public Board map(BoardResponse response) {
        return boardResponseToBoard.map(response);
    }
}
