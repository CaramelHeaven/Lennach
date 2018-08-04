package com.caramelheaven.lennach.datasource.repository;

import com.caramelheaven.lennach.datasource.database.LennachDatabase;
import com.caramelheaven.lennach.datasource.database.entity.iBoard;
import com.caramelheaven.lennach.datasource.model.Board;
import com.caramelheaven.lennach.datasource.model.Thread;
import com.caramelheaven.lennach.datasource.network.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 29.07.2018
 */
public class BoardRepository {

    private final ApiService apiService;
    private final LennachDatabase database;
    private int totalPage = 0;

    @Inject
    public BoardRepository(ApiService apiService, LennachDatabase database) {
        this.apiService = apiService;
        this.database = database;
    }

    public Single<List<Thread>> getBoardByName(String boardName, int page) {
        return apiService.getBoard(boardName, page)
                .doOnSuccess(board -> {
                    totalPage = board.getPages().size();
                    iBoard iBoard = new iBoard(board.getBoard(), board.getBoardName(), board.getBoardSpeed(), board.getBumpLimit());
                    database.boardDao().insertBoard(iBoard);
                })
                .map(Board::getThreads);
    }

    public int getTotalPage() {
        return totalPage;
    }
}
