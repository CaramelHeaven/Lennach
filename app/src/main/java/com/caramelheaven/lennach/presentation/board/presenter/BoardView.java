package com.caramelheaven.lennach.presentation.board.presenter;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.presentation.base.ParentView;

import java.util.List;

public interface BoardView<T> extends ParentView {

    @StateStrategyType(value = SingleStateStrategy.class)
    void showItems(List<T> items);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showError();

    void refreshItems(List<T> items);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showMainBottomBar(boolean flag);
}
