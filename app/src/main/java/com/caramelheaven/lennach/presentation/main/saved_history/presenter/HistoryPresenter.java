package com.caramelheaven.lennach.presentation.main.saved_history.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.presentation.base.BasePresenter;

@InjectViewState
public class HistoryPresenter extends BasePresenter<HistoryView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerError(Throwable throwable) {

    }
}
