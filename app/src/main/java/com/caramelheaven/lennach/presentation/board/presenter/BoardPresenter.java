package com.caramelheaven.lennach.presentation.board.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.di.board.BoardModule;
import com.caramelheaven.lennach.domain.board_use_case.GetBoard;
import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.models.model.board.Usenet;
import com.caramelheaven.lennach.presentation.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class BoardPresenter extends BasePresenter<List<Usenet>, BoardView> {

    private CompositeDisposable disposable;

    @Inject
    GetBoard getBoard;

    public BoardPresenter() {
        Lennach.getComponentsManager()
                .plusMainComponent()
                .plusBoardComponent(new BoardModule())
                .inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("CheckResult")
    private void getData() {
        getViewState().showProgress();
        getBoard.subscribeToData("b")
                .subscribeOn(Schedulers.io())
                .map(Board::getUsenetList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerError);
    }

    @Override
    protected void handlerError(Throwable throwable) {
        Timber.d("error: " + throwable.getMessage());
    }

    @Override
    protected void successfulResult(List<Usenet> result) {
        getViewState().hideProgress();
        getViewState().showItems(result);
    }
}
