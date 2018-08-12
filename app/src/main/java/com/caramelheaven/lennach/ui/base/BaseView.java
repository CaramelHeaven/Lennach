package com.caramelheaven.lennach.ui.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = OneExecutionStateStrategy.class)
public interface BaseView extends MvpView {
    void showProgress();

    void hideProgress();
}