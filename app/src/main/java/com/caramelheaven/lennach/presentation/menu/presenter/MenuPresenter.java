package com.caramelheaven.lennach.presentation.menu.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.presentation.base.BasePresenter;

@InjectViewState
public class MenuPresenter extends BasePresenter<Board, MenuView> {

    public MenuPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerError(Throwable throwable) {

    }

    @Override
    protected void successfulResult(Board result) {

    }
}
