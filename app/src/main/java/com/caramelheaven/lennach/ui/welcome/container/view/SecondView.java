package com.caramelheaven.lennach.ui.welcome.container.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.utils.view.WelcomeButton;

import java.util.List;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface SecondView extends MvpView {
    void showItems(List<WelcomeButton> models);
}
