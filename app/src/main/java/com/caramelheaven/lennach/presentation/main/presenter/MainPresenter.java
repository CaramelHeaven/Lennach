package com.caramelheaven.lennach.presentation.main.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.utils.Constants;
import com.caramelheaven.lennach.utils.channel.Channel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private boolean allowToHide = true, transformToEnter;
    private CompositeDisposable disposable;

    public MainPresenter() {
        disposable = new CompositeDisposable();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Channel.getInstance().getPublishSubject()
                .subscribe(result -> {
                    Timber.d("MAIN channel: " + result.getData());
                    if (result.getData() == Constants.EXIT_FAB_STATE) {
                        Timber.d("IN: " + result.getData());
                        getViewState().menuTransform(false);
                    } else if (result.getData() == Constants.ENTER_FAB_STATE) {
                        Timber.d("IN: " + result.getData());
                        getViewState().menuTransform(true);
                    }
                });
    }

    public void menuBehavior(boolean flag) {
        getViewState().menuBehavior(flag);
        disposable.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setAllowToHide(boolean allowToHide) {
        this.allowToHide = allowToHide;
    }

    public boolean isTransformToEnter() {
        return transformToEnter;
    }

    public boolean isAllowToHide() {
        return allowToHide;
    }
}
