package com.caramelheaven.lennach.ui.main.navigation.add.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.datasource.model.BoardNavModel;
import com.caramelheaven.lennach.ui.base.BaseView;

import java.util.List;
import java.util.Set;

public interface AddDialogView extends BaseView {
    @StateStrategyType(value = SingleStateStrategy.class)
    void showItems(List<BoardNavModel> models);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showSelectedItems(Set<Integer> models);
}
