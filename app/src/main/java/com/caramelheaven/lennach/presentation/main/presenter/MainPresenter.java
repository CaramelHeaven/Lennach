package com.caramelheaven.lennach.presentation.main.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private boolean allowToHide = true, allowToShow = true;

    public MainPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void menuBehavior(boolean flag) {
        getViewState().menuBehavior(flag);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setAllowToHide(boolean allowToHide) {
        this.allowToHide = allowToHide;
    }

    public void setAllowToShow(boolean allowToShow) {
        this.allowToShow = allowToShow;
    }

    public boolean isAllowToHide() {
        return allowToHide;
    }

    public boolean isAllowToShow() {
        return allowToShow;
    }
}
