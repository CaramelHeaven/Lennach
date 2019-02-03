package com.caramelheaven.lennach.models.mapper.board;

import com.caramelheaven.lennach.models.mapper.board.entities.BoardResponseToBoard;
import com.caramelheaven.lennach.models.model.Board;
import com.caramelheaven.lennach.models.network.base.BoardResponse;

/**
 * Created by CaramelHeaven on 16:28, 03/02/2019.
 * Base class for handlers all mappers
 */
public class BoardMapper {
    private final BoardResponseToBoard boardResponseToBoard;

    public BoardMapper(BoardResponseToBoard boardResponseToBoard) {
        this.boardResponseToBoard = boardResponseToBoard;
    }

    public Board map(BoardResponse response){
        return boardResponseToBoard.map(response);
    }
}
