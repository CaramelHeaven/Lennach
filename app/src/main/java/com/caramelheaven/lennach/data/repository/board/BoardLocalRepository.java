package com.caramelheaven.lennach.data.repository.board;

import com.caramelheaven.lennach.data.datasource.database.dao.BoardDao;
import com.caramelheaven.lennach.domain.repositories.BoardDatabaseRepository;
import com.caramelheaven.lennach.models.database.BoardDb;
import com.caramelheaven.lennach.models.mapper.board.BoardMapper;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 16:42, 03/02/2019.
 */
public class BoardLocalRepository implements BoardDatabaseRepository {

    private final BoardDao boardDao;
    private final BoardMapper boardMapper;

    public BoardLocalRepository(BoardDao boardDao, BoardMapper boardMapper) {
        this.boardDao = boardDao;
        this.boardMapper = boardMapper;
    }

    @Override
    public Completable saveFavouriteBoards(List<BoardDb> values) {
        return null;
    }

    @Override
    public Single<List<BoardDb>> getFavouriteBoards() {
        return null;
    }
}
