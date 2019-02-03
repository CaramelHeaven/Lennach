package com.caramelheaven.lennach.data.repository.board;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.domain.repositories.BoardNetworkRepository;
import com.caramelheaven.lennach.models.mapper.board.BoardMapper;
import com.caramelheaven.lennach.models.model.Board;

import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 16:42, 03/02/2019.
 */
public class BoardRemoteRepository implements BoardNetworkRepository {

    private final LennachApiService lennachApiService;
    private final BoardMapper boardMapper;

    public BoardRemoteRepository(LennachApiService lennachApiService, BoardMapper boardMapper) {
        this.lennachApiService = lennachApiService;
        this.boardMapper = boardMapper;
    }

    @Override
    public Single<Board> getBoard(String boardName, int page) {
        return lennachApiService.getBoard(boardName, page)
                .map(boardMapper::map);
    }
}
