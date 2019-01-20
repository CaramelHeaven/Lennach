package com.caramelheaven.lennach.models.mapper.board;

import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.models.model.board.BoardAll;
import com.caramelheaven.lennach.models.network.BoardAllResponse;
import com.caramelheaven.lennach.models.network.BoardResponse;

import java.util.List;

public class BoardMapper {
    private BoardResponseToBoard boardResponseToBoard;
    private BoardAllResponseToBoardAll boardAllResponseToBoardAll;

    public BoardMapper(BoardResponseToBoard boardResponseToBoard,
                       BoardAllResponseToBoardAll boardAllResponseToBoardAll) {
        this.boardResponseToBoard = boardResponseToBoard;
        this.boardAllResponseToBoardAll = boardAllResponseToBoardAll;
    }

    public Board map(BoardResponse response) {
        return boardResponseToBoard.map(response);
    }

    public List<BoardAll> map(BoardAllResponse response) {
        return boardAllResponseToBoardAll.map(response);
    }
}
