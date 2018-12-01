package com.caramelheaven.lennach.presentation.board.presenter;

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
public class BoardPresenter extends BasePresenter<List<Usenet>, BoardView<Usenet>> {

    private CompositeDisposable disposable;

    @Inject
    GetBoard getBoard;


    public BoardPresenter() {
        Timber.d("inject view state");
        disposable = new CompositeDisposable();

        Lennach.getComponentsManager()
                .plusBoardComponent()
                .inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        Lennach.getComponentsManager().clearBoardComponent();
        super.onDestroy();
    }


    @Override
    protected void handlerError(Throwable throwable) {

    }

    @Override
    protected void successfulResult(List<Usenet> result) {
        getViewState().hideProgress();
        getViewState().showItems(result);
    }

    @Override
    protected void getData() {
        getViewState().showProgress();
        disposable.add(getBoard.subscribeToData("b")
                .subscribeOn(Schedulers.io())
                .map(Board::getUsenetList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerError));
    }
}
