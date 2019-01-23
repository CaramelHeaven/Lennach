package com.caramelheaven.lennach.models.mapper.board;

import com.caramelheaven.lennach.models.database.BoardFavouriteDb;
import com.caramelheaven.lennach.models.mapper.Mapper;
import com.caramelheaven.lennach.models.model.board.BoardFavourite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 22:19, 23/01/2019.
 */
public class BoardFavouriteDbToBoardFavourite extends Mapper<List<BoardFavourite>,
        List<BoardFavouriteDb>> {
    @Override
    protected List<BoardFavourite> map(List<BoardFavouriteDb> value) {
        List<BoardFavourite> boardFavourites = new ArrayList<>();
        fillData(boardFavourites, value);

        return boardFavourites;
    }

    @Override
    protected void fillData(List<BoardFavourite> myData, List<BoardFavouriteDb> values) {

    }
}
