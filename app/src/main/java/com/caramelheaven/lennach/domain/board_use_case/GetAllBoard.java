package com.caramelheaven.lennach.domain.board_use_case;

import com.caramelheaven.lennach.domain.base.BaseUseCase;
import com.caramelheaven.lennach.models.model.board.Board;

import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 19:56, 13/01/2019.
 * Class GetAllBoard provide all board for user confirm to add favorite
 */
public class GetAllBoard extends BaseUseCase<Board> {

    @Override
    public Single<Board> subscribeToData() {
        return null;
    }
}
