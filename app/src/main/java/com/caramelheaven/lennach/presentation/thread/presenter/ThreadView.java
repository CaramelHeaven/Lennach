package com.caramelheaven.lennach.presentation.thread.presenter;

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.presentation.base.ParentView;

import java.util.List;

public interface ThreadView<T> extends ParentView {
    @StateStrategyType(value = SingleStateStrategy.class)
    void showItems(List<T> items);
}
