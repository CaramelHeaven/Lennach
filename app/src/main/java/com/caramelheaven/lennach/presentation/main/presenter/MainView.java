package com.caramelheaven.lennach.presentation.main.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.presentation.base.BaseView;

import java.util.List;

public interface MainView<T> extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showItems(List<T> items);
}
