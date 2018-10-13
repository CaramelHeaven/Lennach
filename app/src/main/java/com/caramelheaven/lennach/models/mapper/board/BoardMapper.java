package com.caramelheaven.lennach.models.mapper.board;

import com.caramelheaven.lennach.models.database.BoardEntity;
import com.caramelheaven.lennach.models.database.UsenetEntity;
import com.caramelheaven.lennach.models.model.board_viewer.Board;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;
import com.caramelheaven.lennach.models.network.BoardResponse;

public class BoardMapper {
    private BoardResponseToBoard boardResponseToBoard;
    private BoardEntityToBoard boardEntityToBoard;
    private UsenetToUsenetEntity usenetToUsenetEntity;

    public BoardMapper(BoardResponseToBoard boardResponseToBoard,
                       BoardEntityToBoard boardEntityToBoard,
                       UsenetToUsenetEntity usenetToUsenetEntity) {
        this.boardResponseToBoard = boardResponseToBoard;
        this.boardEntityToBoard = boardEntityToBoard;
        this.usenetToUsenetEntity = usenetToUsenetEntity;
    }

    public Board map(BoardResponse boardResponse) {
        return boardResponseToBoard.map(boardResponse);
    }

    public BoardEntity map(Board board) {
        return boardEntityToBoard.map(board);
    }

    public UsenetEntity map(Usenet usenet) {
        return usenetToUsenetEntity.map(usenet);
    }
}
