package com.caramelheaven.lennach.domain.board_use_cases;

import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.models.model.board_viewer.Board;

import io.reactivex.Single;
import timber.log.Timber;

public class GetBoard {
    private final BoardRepository boardRepository;
    private int pageIndex = 1;

    public GetBoard(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Single<Board> createUseCase(String boardName) {
        Timber.d("board repository: " + boardRepository.toString());
        Timber.d("board name: " + boardName + " and page: " + pageIndex);
        return boardRepository.getBoard(boardName, pageIndex);
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        Timber.d("pageIndex: " + pageIndex);
    }

    public int getPageIndex() {
        return pageIndex;
    }
}
