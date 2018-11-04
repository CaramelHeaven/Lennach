package com.caramelheaven.lennach.presentation.base;

import com.arellomobile.mvp.MvpPresenter;

public abstract class BasePresenter<S extends BaseView> extends MvpPresenter<S> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void handlerError(Throwable throwable);
}
