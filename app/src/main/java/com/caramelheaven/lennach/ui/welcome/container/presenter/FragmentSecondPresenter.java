package com.caramelheaven.lennach.ui.welcome.container.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.ui.welcome.container.view.SecondView;

@InjectViewState
public class FragmentSecondPresenter extends MvpPresenter<SecondView> {

    public FragmentSecondPresenter() {
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
