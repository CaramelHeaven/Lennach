package com.caramelheaven.lennach.data.repository.board;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.models.mapper.board.BoardMapper;
import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.models.model.board.BoardAll;

import java.util.List;

import io.reactivex.Single;

public class BoardRemoteRepository implements BoardRepository {

    private final LennachApiService apiService;
    private final BoardMapper boardMapper;

    public BoardRemoteRepository(LennachApiService apiService, BoardMapper boardMapper) {
        this.apiService = apiService;
        this.boardMapper = boardMapper;
    }

    @Override
    public Single<Board> getBoard(String boardName, int page) {
        return apiService.getBoard(boardName, page)
                .map(boardMapper::map);
    }

    @Override
    public Single<List<BoardAll>> getAllBoards() {
        return apiService.getAllBoards()
                .map(boardMapper::map);
    }
}
