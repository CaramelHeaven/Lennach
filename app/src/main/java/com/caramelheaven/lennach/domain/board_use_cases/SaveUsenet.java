package com.caramelheaven.lennach.domain.board_use_cases;

import com.caramelheaven.lennach.data.repository.board.BoardLocalRepository;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;

import io.reactivex.Completable;

public class SaveUsenet {
    private final BoardLocalRepository boardLocalRepository;

    public SaveUsenet(BoardLocalRepository boardLocalRepository) {
        this.boardLocalRepository = boardLocalRepository;
    }

    public Completable saveThread(Usenet usenet, boolean isFavourite) {
        return boardLocalRepository.saveThread(usenet, isFavourite);
    }
}
