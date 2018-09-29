package com.caramelheaven.lennach.presentation.board.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.di.board.usenet_list.UsenetListModule;
import com.caramelheaven.lennach.domain.board_use_cases.GetBoard;
import com.caramelheaven.lennach.models.model.board_viewer.Board;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class BoardPresenter extends MvpPresenter<BoardView<Usenet>> {

    private int currentPage = 0;
    private int totalPage = 0;
    private boolean isLoading = false;
    private String boardName;
    private CompositeDisposable disposable;

    @Inject
    GetBoard getBoard;

    public BoardPresenter(String boardName) {
        initConstructor(boardName);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadThreads();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    private void initConstructor(String boardName) {
        Lennach.getComponentsManager()
                .plusBoardComponent()
                .usenetListComponent(new UsenetListModule())
                .inject(this);
        disposable = new CompositeDisposable();
        this.boardName = boardName;
    }

    private void loadThreads() {
        Timber.d("loadThreads");
        disposable.add(getBoard.createUseCase(boardName)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(item -> {
                    getViewState().showLoading();
                    isLoading = true;
                })
                .doOnEvent((board, throwable) -> {
                    totalPage = board.getPages().get(board.getPages().size() - 1);
                    currentPage = board.getCurrentPage();
                })
                .map(Board::getUsenetList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulLoad, this::unsuccessfulLoad));
    }

    private void successfulLoad(List<Usenet> usenets) {
        Timber.d("successFulLoad");
        isLoading = false;
        getViewState().showItems(usenets);
        getViewState().hideLoading();
    }

    private void unsuccessfulLoad(Throwable throwable) {
        Timber.d("unsuccessful load");
        Timber.d("throwable: %s", throwable.getMessage());
        Timber.d("throwable: %s", throwable.getCause());
    }

    public void loadNextPage() {
        if (currentPage != totalPage) {
            getBoard.setPageIndex(currentPage++);
            loadThreads();
        }
    }

    public boolean isLoading() {
        return isLoading;
    }

    public boolean isLastPage() {
        return currentPage == totalPage;
    }
}
