package com.caramelheaven.lennach.presentation.board_choose.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 19:26, 13/01/2019.
 */
public interface BoardChooseView<T> extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void updateValues(List<T> values);
}
