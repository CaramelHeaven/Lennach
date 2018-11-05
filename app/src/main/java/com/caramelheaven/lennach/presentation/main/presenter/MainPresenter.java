package com.caramelheaven.lennach.presentation.main.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private boolean allowToHide = true;
    private int lastScrollState = 5;
    private CompositeDisposable disposable;

    public MainPresenter() {
        disposable = new CompositeDisposable();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
