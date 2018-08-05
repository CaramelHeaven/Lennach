package com.caramelheaven.lennach.ui.board.presenter;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostFileThread;

import java.util.List;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface BoardView extends MvpView {
    void showProgress();

    void hideProgress();

    void hideRefreshing();

    @StateStrategyType(value = SingleStateStrategy.class)
    void showRetryView(String cause);

    @StateStrategyType(value = SingleStateStrategy.class)
    void hideRetryView();

    @StateStrategyType(value = SkipStrategy.class)
    void showError();

    @StateStrategyType(value = SingleStateStrategy.class)
    void refteshItems(List<PostFileThread>  postsInThreads);

    @StateStrategyType(value = SingleStateStrategy.class)
    void showItems(List<PostFileThread>  postsInThreads);
}
