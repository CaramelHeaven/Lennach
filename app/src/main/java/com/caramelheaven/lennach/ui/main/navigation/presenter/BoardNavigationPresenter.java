package com.caramelheaven.lennach.ui.main.navigation.presenter;

import com.arellomobile.mvp.MvpPresenter;

public class BoardNavigationPresenter extends MvpPresenter<BoardNavigationView> {

    public BoardNavigationPresenter(){

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        onLoadBoards();
    }

    private void onLoadBoards() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
