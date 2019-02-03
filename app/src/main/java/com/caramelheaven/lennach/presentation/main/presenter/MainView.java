package com.caramelheaven.lennach.presentation.main.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.presentation.base.view.BaseMainView;

/**
 * Created by CaramelHeaven on 14:52, 03/02/2019.
 */
public interface MainView extends BaseMainView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showData();
}