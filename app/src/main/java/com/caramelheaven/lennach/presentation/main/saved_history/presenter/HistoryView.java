package com.caramelheaven.lennach.presentation.main.saved_history.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.presentation.base.BaseView;

import java.util.List;

public interface HistoryView<T> extends BaseView {

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void updateRecentlyAdapter(List<T> items);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void updateFavouriteAdapter(List<T> usenets);
}
