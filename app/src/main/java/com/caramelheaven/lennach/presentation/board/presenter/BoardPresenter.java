package com.caramelheaven.lennach.presentation.board.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.domain.board_use_case.GetBoard;
import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.models.model.board.Usenet;
import com.caramelheaven.lennach.presentation.base.BasePresenter;
import com.caramelheaven.lennach.utils.bus.models.ActionThread;
import com.caramelheaven.lennach.utils.bus.models.HandlerViewPagerData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class BoardPresenter extends BasePresenter<List<Usenet>, BoardView<Usenet>> {

    private CompositeDisposable disposable;
    private String board;

    @Inject
    GetBoard getBoard;


    public BoardPresenter(String board) {
        this.board = board;
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
        disposable.add(getBoard.subscribeToData(board)
                .subscribeOn(Schedulers.io())
                .map(Board::getUsenetList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerError));
    }

    public HandlerViewPagerData mappingUsenet(Usenet usenet) {
        ActionThread thread = new ActionThread(board, usenet.getThreadNum());
        HandlerViewPagerData data = new HandlerViewPagerData(thread);

        return data;
    }
}
