package com.caramelheaven.lennach.domain.board_use_case;

import com.caramelheaven.lennach.domain.BoardSaveRepository;
import com.caramelheaven.lennach.domain.base.BaseUseCase;
import com.caramelheaven.lennach.models.model.board.BoardFavourite;

import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 19:58, 13/01/2019.
 * GetFavouriteBoards provide favourite boards which user added in the database
 */
public class GetFavouriteBoards extends BaseUseCase<Single<BoardFavourite>> {

    private final BoardSaveRepository repository;

    public GetFavouriteBoards(BoardSaveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<BoardFavourite> subscribeToData() {
        //repository.
        return null;
    }
}
