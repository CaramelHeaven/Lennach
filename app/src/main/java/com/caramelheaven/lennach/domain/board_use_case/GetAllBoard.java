package com.caramelheaven.lennach.domain.board_use_case;

import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.domain.base.BaseUseCase;
import com.caramelheaven.lennach.models.model.board.BoardAll;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 19:56, 13/01/2019.
 * Class GetAllBoard provide all board for user confirm to add favorite
 */
public class GetAllBoard extends BaseUseCase<List<BoardAll>> {

    private final BoardRepository boardRepository;

    public GetAllBoard(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Single<List<BoardAll>> subscribeToData() {
        return boardRepository.getAllBoards();
    }
}
