package com.caramelheaven.lennach.presentation.main.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

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
}
