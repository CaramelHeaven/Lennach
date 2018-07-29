package com.caramelheaven.lennach.ui.board.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.datasource.database.entity.iThread;

import java.util.List;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface BoardView extends MvpView {
    void showProgress();

    void hideProgress();

    void hideRefreshing();

    void showRetryView(String cause);

    void hideRetryView();

    @StateStrategyType(value = SkipStrategy.class)
    void showError();

    @StateStrategyType(value = SingleStateStrategy.class)
    void refteshItems(List<iThread> iThreads);

    @StateStrategyType(value = SingleStateStrategy.class)
    void showItems(List<iThread> iThreads);
}
