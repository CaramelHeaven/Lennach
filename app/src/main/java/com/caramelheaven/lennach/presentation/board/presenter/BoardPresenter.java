package com.caramelheaven.lennach.presentation.board.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.domain.board_use_case.GetBoard;
import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.models.model.board.Usenet;
import com.caramelheaven.lennach.presentation.base.BasePresenter;
import com.caramelheaven.lennach.utils.bus.models.ActionThread;
import com.caramelheaven.lennach.utils.bus.models.HandlerViewPagerData;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class BoardPresenter extends BasePresenter<List<Usenet>, BoardView<Usenet>> {

    private CompositeDisposable disposable;
    private String board;
    private int currentPage = 0, totalPages = 0;
    private boolean isLoading = false;
    private Set<Usenet> usenetList;

    @Inject
    GetBoard getBoard;


    public BoardPresenter(String board) {
        this.board = board;
        Timber.d("inject view state");
        disposable = new CompositeDisposable();
        usenetList = new LinkedHashSet<>();

        Lennach.getComponentsManager()
                .plusBoardComponent()
                .inject(this);

        getBoard.setBoardName("b");
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        Lennach.getComponentsManager().clearBoardComponent();
        super.onDestroy();
    }


    @Override
    protected void handlerError(Throwable throwable) {

    }

    @Override
    protected void successfulResult(List<Usenet> result) {
        usenetList.addAll(result);
        getViewState().hideProgress();

        Timber.d("check: ");
        getViewState().showItems(new ArrayList<>(usenetList), currentPage == 1);
    }

    @Override
    protected void getData() {
        disposable.add(getBoard.subscribeToData()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(item -> {
                    getViewState().showProgress();
                    isLoading = true;
                })
                .doOnSuccess(board -> {
                    totalPages = board.getPages().get(board.getPages().size() - 1);
                    currentPage = board.getCurrentPage();
                    Timber.d("check: " + currentPage);

                    isLoading = false;
                })
                .map(Board::getUsenetList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerError));
    }

    public HandlerViewPagerData mappingUsenet(Usenet usenet) {
        Timber.d("getBoard:");
        ActionThread thread = new ActionThread(getBoard.getBoardName(), usenet.getNum());
        HandlerViewPagerData data = new HandlerViewPagerData(thread);

        return data;
    }

    public void loadNextPage() {
        Timber.d("current page: " + currentPage + " all: " + totalPages);
        if (currentPage != totalPages - 1) {
            getBoard.setPage(++currentPage);

            getData();
        }
    }

    public boolean isLastPage() {
        return totalPages == currentPage;
    }

    public boolean isLoading() {
        return isLoading;
    }
}
