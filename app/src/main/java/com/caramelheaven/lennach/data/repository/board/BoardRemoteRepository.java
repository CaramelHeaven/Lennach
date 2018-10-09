package com.caramelheaven.lennach.data.repository.board;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.models.mapper.board.BoardMapper;
import com.caramelheaven.lennach.models.model.board_viewer.Board;
import com.caramelheaven.lennach.models.network.BoardResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import timber.log.Timber;

public class BoardRemoteRepository implements BoardRepository {

    private final BoardLocalRepository boardLocalRepository;
    private final LennachApiService apiService;
    private final BoardMapper boardMapper;

    public BoardRemoteRepository(BoardLocalRepository boardLocalRepository,
                                 LennachApiService apiService, BoardMapper boardMapper) {
        this.boardLocalRepository = boardLocalRepository;
        this.apiService = apiService;
        this.boardMapper = boardMapper;
    }

    @Override
    public Single<Board> getBoard(String boardName, int page) {
        return apiService
                .getBoard(boardName, page)
                .compose(saveToDatabaseBoard());
    }

    private SingleTransformer<BoardResponse, Board> saveToDatabaseBoard() {
        return board -> board.map(boardMapper::map)
                .doAfterSuccess(result -> {
                    Timber.d("result size: " + result.getUsenetList().size());
                });
    }
}
