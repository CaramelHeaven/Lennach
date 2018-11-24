package com.caramelheaven.lennach.presentation.base;

import com.arellomobile.mvp.MvpPresenter;

public abstract class BasePresenter<T, S extends BaseView> extends MvpPresenter<S> {

    protected abstract void handlerError(Throwable throwable);

    protected abstract void successfulResult(T result);
}
