package com.caramelheaven.lennach.presentation.board.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.di.ComponentsManager;
import com.caramelheaven.lennach.domain.board_use_cases.GetBoard;
import com.caramelheaven.lennach.presentation.base.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by CaramelHeaven on 15:52, 03/02/2019.
 */
@InjectViewState
public class BoardPresenter extends BasePresenter<BoardView> {

    public BoardPresenter() {
        super();
    }

    @Override
    protected void baseHandlerRepository() {

    }
}
