package com.caramelheaven.lennach.domain.board_use_case;

import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.domain.base.BaseUseCase;
import com.caramelheaven.lennach.models.model.board.Board;

import io.reactivex.Completable;
import io.reactivex.Single;
import timber.log.Timber;

public class GetBoard extends BaseUseCase<Single<Board>> {
    private final BoardRepository repository;
    private int pageIndex = 1;
    private String boardName;

    public GetBoard(BoardRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<Board> subscribeToData() {
        Timber.d("board name: " + boardName);
        return repository.getBoard(boardName, pageIndex);
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setPage(int page) {
        this.pageIndex = page;
    }
}
