package com.caramelheaven.lennach.models.mapper;

import com.caramelheaven.lennach.models.database.BoardEntity;
import com.caramelheaven.lennach.models.model.board_viewer.Board;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;
import com.caramelheaven.lennach.models.network.BoardResponse;

import java.util.List;

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

    public BoardEntity map(Board board) {
        return boardEntityToBoard.map(board);
    }
}
