package com.caramelheaven.lennach.presentation.board.presenter;

import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.domain.board_use_cases.GetBoard;
import com.caramelheaven.lennach.presentation.base.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by CaramelHeaven on 18:08, 03/02/2019.
 */
public class BoardPresenter extends BasePresenter<BoardView> {

    @Inject
    GetBoard getBoard;

    public BoardPresenter() {
        super();
        Lennach.getComponentsManager()
                .plusBoardComponent()
                .inject(this);
    }

    @Override
    protected void baseHandlerRepository() {

    }
}
