package com.caramelheaven.lennach.di.thread.post_list;

import com.caramelheaven.lennach.di.ActivityScope;
import com.caramelheaven.lennach.presentation.thread.presenter.ThreadPresenter;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = PostListModule.class)
public interface PostListComponent {
    void inject(ThreadPresenter threadPresenter);
}
