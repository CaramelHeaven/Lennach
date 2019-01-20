package com.caramelheaven.lennach.presentation.base;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<T, S extends BaseView> extends MvpPresenter<S> {

    protected final CompositeDisposable disposable;

    public BasePresenter() {
        this.disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getData();
    }

    @Override
    public void onDestroy() {
        clearData();
        disposable.clear();
        super.onDestroy();
    }

    protected abstract void handlerError(Throwable throwable);

    protected abstract void successfulResult(T result);

    protected abstract void getData();

    protected abstract void clearData();
}
