package com.caramelheaven.lennach.ui.board.presenter;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostFileThread;
import com.caramelheaven.lennach.ui.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
public interface BoardView<T> extends BaseView {

    void hideRefreshing();

    @StateStrategyType(value = SkipStrategy.class)
    void showError();

    @StateStrategyType(value = SingleStateStrategy.class)
    void refteshItems(List<T>  models);

    @StateStrategyType(value = SingleStateStrategy.class)
    void showItems(List<T>  models);
}
