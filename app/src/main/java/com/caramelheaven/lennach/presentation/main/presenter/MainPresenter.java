package com.caramelheaven.lennach.presentation.main.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.Lennach;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public MainPresenter() {
        Lennach.getComponentsManager()
                .plusMainComponent();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Lennach.getComponentsManager()
                .clearBoardComponent();
    }
}
