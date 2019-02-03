package com.caramelheaven.lennach.domain.board_use_cases;

import com.caramelheaven.lennach.domain.repositories.BoardNetworkRepository;
import com.caramelheaven.lennach.domain.base.BaseUseCase;
import com.caramelheaven.lennach.models.model.Board;

import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 16:38, 03/02/2019.
 */
public class GetBoard extends BaseUseCase<Single<Board>> {

    private final BoardNetworkRepository repository;
    private int pageIndex = 1;
    private String boardName;

    public GetBoard(BoardNetworkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<Board> handler() {
        return null;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public void setPage(int page) {
        this.pageIndex = page;
    }
}
