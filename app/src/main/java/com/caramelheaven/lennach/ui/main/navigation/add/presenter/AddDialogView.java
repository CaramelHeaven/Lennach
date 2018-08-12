package com.caramelheaven.lennach.ui.main.navigation.add.presenter;

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.datasource.model.BoardNavModel;
import com.caramelheaven.lennach.ui.base.BaseView;

import java.util.List;

public interface AddDialogView extends BaseView {
    @StateStrategyType(value = SingleStateStrategy.class)
    void showItems(List<BoardNavModel> models);
}
