package com.caramelheaven.lennach.ui.welcome.container.view;

import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface FirstView extends MvpView {
    void showItems(List<RelativeLayout> models);
}
