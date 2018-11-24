package com.caramelheaven.lennach.presentation.main.fragment.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.presentation.base.BasePresenter;

@InjectViewState
public class MainFrgmPresenter extends MvpPresenter<MainFrgmView> {

    public MainFrgmPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
