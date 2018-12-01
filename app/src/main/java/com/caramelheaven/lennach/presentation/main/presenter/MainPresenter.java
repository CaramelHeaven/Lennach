package com.caramelheaven.lennach.presentation.main.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.Lennach;
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
public class MainPresenter extends MvpPresenter<MainView> {

    private CompositeDisposable disposable;

    /**
     * enterImageClickPosition - position for click on image view
     * exitImageSwipePosition - position for swipe image when we are inside gallery
     */
    private int enterImageClickPosition;
    private int exitImageSwipePosition;

    public MainPresenter() {
        Timber.d("inject view state");
        disposable = new CompositeDisposable();

        Lennach.getComponentsManager()
                .plusMainComponent()
                .inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }


    @Override
    public void onDestroy() {
        disposable.clear();
        Lennach.getComponentsManager().clearMainComponent();

        super.onDestroy();
    }


    public int getEnterImageClickPosition() {
        return enterImageClickPosition;
    }

    public void setEnterImageClickPosition(int enterImageClickPosition) {
        this.enterImageClickPosition = enterImageClickPosition;
    }

    public int getExitImageSwipePosition() {
        return exitImageSwipePosition;
    }

    public void setExitImageSwipePosition(int exitImageSwipePosition) {
        this.exitImageSwipePosition = exitImageSwipePosition;
    }
}
