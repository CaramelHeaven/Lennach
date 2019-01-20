package com.caramelheaven.lennach.presentation.board_choose.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.domain.board_use_case.GetAllBoard;
import com.caramelheaven.lennach.models.model.board.BoardAll;
import com.caramelheaven.lennach.models.network.BoardAllResponse;
import com.caramelheaven.lennach.presentation.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CaramelHeaven on 19:25, 13/01/2019.
 */
@InjectViewState
public class BoardChoosePresenter extends BasePresenter<List<BoardAll>, BoardChooseView<BoardAll>> {

    @Inject
    GetAllBoard getAllBoard;

    public BoardChoosePresenter() {
        super();
        Lennach.getComponentsManager()
                .plusBoardChooseComponent()
                .inject(this);
    }

    @Override
    protected void handlerError(Throwable throwable) {

    }

    @Override
    protected void successfulResult(List<BoardAll> result) {

    }

    @Override
    protected void getData() {
        getViewState().showProgress();

        disposable.add(getAllBoard.subscribeToData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerError));
    }

    @Override
    protected void clearData() {
        Lennach.getComponentsManager()
                .clearBoardChooseComponent();
    }
}
