package com.caramelheaven.lennach.models.mapper.board;

import com.caramelheaven.lennach.models.model.board.BoardFavourite;
import com.caramelheaven.lennach.models.network.BoardAllResponse;
import com.caramelheaven.lennach.models.network.all_board_models.BoardAllNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 21:22, 20/01/2019.
 */
public class BoardAllResponseToBoardAll {

    public List<BoardFavourite> map(BoardAllResponse response) {
        List<BoardFavourite> boardAll = new ArrayList<>();
        fillData(boardAll, response);

        return boardAll;
    }

    private void fillData(List<BoardFavourite> boardAll, BoardAllResponse response) {
        for (BoardAllNetwork network : response.sumAllData()) {
            BoardFavourite board = new BoardFavourite(network.getId(), network.getName(),
                    network.getCategory());

            boardAll.add(board);
        }
    }
}
