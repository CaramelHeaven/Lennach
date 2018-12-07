package com.caramelheaven.lennach.di.thread;

import com.caramelheaven.lennach.presentation.thread.presenter.ThreadPresenter;

import dagger.Subcomponent;

/**
 * Created by CaramelHeaven on 22:27, 07/12/2018.
 */
@ThreadScope
@Subcomponent(modules = {ThreadModule.class})
public interface ThreadComponent {
    void inject(ThreadPresenter presenter);
}
