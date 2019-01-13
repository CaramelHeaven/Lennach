package com.caramelheaven.lennach.presentation.navigation.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.models.model.common.Delegatable;
import com.caramelheaven.lennach.presentation.base.BaseView;

import java.util.List;

public interface NavigationView extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showFavouriteData(List<Delegatable> items);
}
