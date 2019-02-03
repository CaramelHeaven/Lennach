package com.caramelheaven.lennach.presentation.base.view;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by CaramelHeaven on 14:39, 03/02/2019.
 */
@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface BaseView extends BaseMainView {
    void showProgress();

    void hideProgress();
}
