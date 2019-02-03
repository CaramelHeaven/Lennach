package com.caramelheaven.lennach.domain.board_use_cases;

import com.caramelheaven.lennach.domain.base.BaseUseCase;
import com.caramelheaven.lennach.domain.repositories.BoardDatabaseRepository;
import com.caramelheaven.lennach.models.database.BoardDb;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 18:30, 03/02/2019.
 */
public class GetFavouriteBoards extends BaseUseCase<Single<List<BoardDb>>> {
    private final BoardDatabaseRepository repository;

    public GetFavouriteBoards(BoardDatabaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<BoardDb>> handler() {
        return repository.getFavouriteBoards();
    }
}
