package com.caramelheaven.lennach.presentation.base;

import com.arellomobile.mvp.MvpPresenter;

public abstract class BasePresenter<T, S extends BaseView> extends MvpPresenter<S> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getData();
    }

    protected abstract void handlerError(Throwable throwable);

    protected abstract void successfulResult(T result);

    protected abstract void getData();
}
