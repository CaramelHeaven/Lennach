package com.caramelheaven.lennach.presentation.board.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.models.model.board.Usenet;
import com.caramelheaven.lennach.presentation.base.BasePresenter;

import java.util.List;

@InjectViewState
public class BoardPresenter extends BasePresenter<List<Usenet>, BoardView> {

    public BoardPresenter(){

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
    protected void successfulResult(List<Usenet> result) {

    }
}
