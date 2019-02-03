package com.caramelheaven.lennach.domain.board_use_cases;

import com.caramelheaven.lennach.domain.base.BaseUseCase;
import com.caramelheaven.lennach.domain.repositories.BoardDatabaseRepository;

import java.util.List;

import io.reactivex.Completable;

/**
 * Created by CaramelHeaven on 18:33, 03/02/2019.
 */
public class PutFavouriteBoards extends BaseUseCase<Completable> {
    private final BoardDatabaseRepository repository;

    public PutFavouriteBoards(BoardDatabaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Completable handler() {
        //return repository.saveFavouriteBoards()
        return null;
    }
}
