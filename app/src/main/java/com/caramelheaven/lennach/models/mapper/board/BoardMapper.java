package com.caramelheaven.lennach.models.mapper.board;

import com.caramelheaven.lennach.models.database.BoardFavouriteDb;
import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.models.model.board.BoardFavourite;
import com.caramelheaven.lennach.models.network.BoardAllResponse;
import com.caramelheaven.lennach.models.network.BoardResponse;

import java.util.List;

public class BoardMapper {
    private BoardResponseToBoard boardResponseToBoard;
    private BoardAllResponseToBoardAll boardAllResponseToBoardAll;
    private BoardAllToBoardFavouriteDb boardAllToBoardFavouriteDb;
    private BoardFavouriteDbToBoardFavourite boardFavouriteDbToBoardFavourite;

    public BoardMapper(BoardResponseToBoard boardResponseToBoard,
                       BoardAllResponseToBoardAll boardAllResponseToBoardAll,
                       BoardAllToBoardFavouriteDb boardAllToBoardFavouriteDb,
                       BoardFavouriteDbToBoardFavourite boardFavouriteDbToBoardFavourite) {
        this.boardResponseToBoard = boardResponseToBoard;
        this.boardAllResponseToBoardAll = boardAllResponseToBoardAll;
        this.boardAllToBoardFavouriteDb = boardAllToBoardFavouriteDb;
        this.boardFavouriteDbToBoardFavourite = boardFavouriteDbToBoardFavourite;
    }

    public Board map(BoardResponse response) {
        return boardResponseToBoard.map(response);
    }

    public List<BoardFavourite> map(BoardAllResponse response) {
        return boardAllResponseToBoardAll.map(response);
    }

    public List<BoardFavouriteDb> map(List<BoardFavourite> boardAllList) {
        return boardAllToBoardFavouriteDb.map(boardAllList);
    }

    public List<BoardFavourite> mapFavourite(List<BoardFavouriteDb> boardFavouriteDbs) {
        return boardFavouriteDbToBoardFavourite.map(boardFavouriteDbs);
    }

}
