package com.caramelheaven.lennach.models.mapper.board;

import com.caramelheaven.lennach.models.model.board.BoardAll;
import com.caramelheaven.lennach.models.network.BoardAllResponse;
import com.caramelheaven.lennach.models.network.all_board_models.BoardAllNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 21:22, 20/01/2019.
 */
public class BoardAllResponseToBoardAll {

    public List<BoardAll> map(BoardAllResponse response) {
        List<BoardAll> boardAll = new ArrayList<>();
        fillData(boardAll, response);

        return boardAll;
    }

    private void fillData(List<BoardAll> boardAll, BoardAllResponse response) {
        for (BoardAllNetwork network : response.sumAllData()) {
            BoardAll board = new BoardAll(network.getId(), network.getName(),
                    network.getCategory());

            boardAll.add(board);
        }
    }
}
