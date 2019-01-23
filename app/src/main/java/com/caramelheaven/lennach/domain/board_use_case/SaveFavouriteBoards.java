package com.caramelheaven.lennach.domain.board_use_case;

import com.caramelheaven.lennach.domain.BoardSaveRepository;
import com.caramelheaven.lennach.domain.base.BaseUseCase;
import com.caramelheaven.lennach.models.model.board.BoardFavourite;

import java.util.List;

import io.reactivex.Completable;

/**
 * Created by CaramelHeaven on 20:57, 23/01/2019.
 */
public class SaveFavouriteBoards extends BaseUseCase<Completable> {

    private List<BoardFavourite> data;

    private final BoardSaveRepository repository;

    public SaveFavouriteBoards(BoardSaveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Completable subscribeToData() {
        return repository.saveFavouriteBoards(data);
    }

    public List<BoardFavourite> getData() {
        return data;
    }

    public void setData(List<BoardFavourite> data) {
        this.data = data;
    }
}
