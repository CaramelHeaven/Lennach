package com.caramelheaven.lennach.di.thread.thread_favourite;

import com.caramelheaven.lennach.di.ActivityScope;
import com.caramelheaven.lennach.presentation.main.saved_history.presenter.HistoryPresenter;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = ThreadFavouriteModule.class)
public interface ThreadFavouriteComponent {
    void inject(HistoryPresenter presenter);
}
