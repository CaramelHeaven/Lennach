package com.caramelheaven.lennach.data.repository.board;

import com.caramelheaven.lennach.data.datasource.database.dao.BoardDao;
import com.caramelheaven.lennach.domain.BoardSaveRepository;
import com.caramelheaven.lennach.models.mapper.board.BoardMapper;
import com.caramelheaven.lennach.models.model.board.BoardFavourite;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class BoardLocalRepository implements BoardSaveRepository {
    private final BoardMapper boardMapper;
    private final BoardDao boardDao;

    public BoardLocalRepository(BoardMapper boardMapper, BoardDao boardDao) {
        this.boardMapper = boardMapper;
        this.boardDao = boardDao;
    }

    @Override
    public Completable saveFavouriteBoards(List<BoardFavourite> values) {
        return Completable.fromAction(() ->
                boardDao.addListFavouriteBoards(boardMapper.map(values)));
    }

    @Override
    public Single<List<BoardFavourite>> getFavouriteBoards() {
        return boardDao.getFavouritesBoards()
                .map(boardMapper::mapFavourite);
    }
}
