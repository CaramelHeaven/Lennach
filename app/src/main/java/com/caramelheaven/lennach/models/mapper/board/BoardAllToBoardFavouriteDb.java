package com.caramelheaven.lennach.models.mapper.board;

import com.caramelheaven.lennach.models.database.BoardFavouriteDb;
import com.caramelheaven.lennach.models.mapper.Mapper;
import com.caramelheaven.lennach.models.model.board.BoardFavourite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 20:54, 23/01/2019.
 */
public class BoardAllToBoardFavouriteDb extends Mapper<List<BoardFavouriteDb>, List<BoardFavourite>> {

    @Override
    protected List<BoardFavouriteDb> map(List<BoardFavourite> value) {
        List<BoardFavouriteDb> boardFavouriteDbs = new ArrayList<>();
        fillData(boardFavouriteDbs, value);

        return boardFavouriteDbs;
    }

    @Override
    protected void fillData(List<BoardFavouriteDb> myData, List<BoardFavourite> values) {

    }
}
