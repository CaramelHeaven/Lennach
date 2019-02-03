package com.caramelheaven.lennach.models.mapper.board.entities;

import com.caramelheaven.lennach.models.mapper.Mapper;
import com.caramelheaven.lennach.models.model.board_list.BoardViewer;
import com.caramelheaven.lennach.models.network.list_of_boards.BoardAllResponse;

import java.util.List;

/**
 * Created by CaramelHeaven on 18:48, 03/02/2019.
 */
public class BoardAllResponseToBoardViewer extends Mapper<List<BoardViewer>, BoardAllResponse> {

    @Override
    public List<BoardViewer> map(BoardAllResponse enterData) {
        return null;
    }

    @Override
    protected void fillData(List<BoardViewer> outputData, BoardAllResponse enterData) {

    }
}
