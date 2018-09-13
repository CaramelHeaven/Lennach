package com.caramelheaven.lennach.ui.thread.presenter;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostsHelper;
import com.caramelheaven.lennach.datasource.model.Post;

import java.util.List;

public interface ThreadView extends MvpView {
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
    void refteshItems(List<Post> posts);

    @StateStrategyType(value = SingleStateStrategy.class)
    void showItems(List<Post> posts);
}