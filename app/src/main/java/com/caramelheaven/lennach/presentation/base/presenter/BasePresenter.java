package com.caramelheaven.lennach.presentation.base.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.presentation.base.view.BaseMainView;
import com.caramelheaven.lennach.presentation.base.view.BaseView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by CaramelHeaven on 14:39, 03/02/2019.
 */
public abstract class BasePresenter<S extends BaseMainView> extends MvpPresenter<S> {

    protected final CompositeDisposable disposable;

    protected abstract void baseHandlerRepository();

    public BasePresenter() {
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        baseHandlerRepository();
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}
