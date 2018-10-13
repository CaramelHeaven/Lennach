package com.caramelheaven.lennach.data.repository.board;

import com.caramelheaven.lennach.data.datasource.database.ThreadDao;
import com.caramelheaven.lennach.models.mapper.board.BoardMapper;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;

import io.reactivex.Completable;
import io.reactivex.Single;

public class BoardLocalRepository {
    private final BoardMapper boardMapper;
    private final ThreadDao threadDao;

    public BoardLocalRepository(BoardMapper boardMapper, ThreadDao threadDao) {
        this.boardMapper = boardMapper;
        this.threadDao = threadDao;
    }

    public Completable saveThread(Usenet usenet) {
        return Completable.fromAction(() -> threadDao.insertThread(boardMapper.map(usenet)));
    }
}
