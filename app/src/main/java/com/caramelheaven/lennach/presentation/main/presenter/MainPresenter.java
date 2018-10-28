package com.caramelheaven.lennach.presentation.main.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.utils.Constants;
import com.caramelheaven.lennach.utils.channel.Channel;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private boolean allowToHide = true;
    private int lastScrollState = 5;
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
                    switch (result.getData()) {
                        case Constants.EXIT_FAB_STATE:
                            getViewState().menuTransform(false);
                            break;
                        case Constants.ENTER_FAB_STATE:
                            Timber.d("IN: " + result.getData());
                            getViewState().menuTransform(true);
                            break;
                        case Constants.HIDE_BOTTOM_BAR_SCROLL:
                            if (Constants.HIDE_BOTTOM_BAR_SCROLL != lastScrollState) {
                                getViewState().menuBehaviorScroll(Constants.SMOOTH_HIDE_BOTTOM_APP_BAR);
                                lastScrollState = Constants.HIDE_BOTTOM_BAR_SCROLL;
                                Timber.d("HIDE BOTTOM SCROLL");
                            }
                            break;
                        case Constants.SHOW_BOTTOM_BAR_SCROLL:
                            if (Constants.SHOW_BOTTOM_BAR_SCROLL != lastScrollState) {
                                getViewState().menuBehaviorScroll(Constants.SMOOTH_SHOW_BOTTOM_APP_BAR);
                                lastScrollState = Constants.SHOW_BOTTOM_BAR_SCROLL;
                                Timber.d("SHOW BOTTOM SCROLL");
                            }
                            break;
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

    public boolean isAllowToHide() {
        return allowToHide;
    }
}
