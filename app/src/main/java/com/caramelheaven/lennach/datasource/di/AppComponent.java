package com.caramelheaven.lennach.datasource.di;

import android.app.Application;

import com.caramelheaven.lennach.datasource.di.module.AppModule;
import com.caramelheaven.lennach.datasource.di.module.RepositoryModule;
import com.caramelheaven.lennach.ui.board.presenter.BoardPresenter;
import com.caramelheaven.lennach.ui.thread.presenter.MessagePresenter;
import com.caramelheaven.lennach.ui.thread.presenter.ThreadPresenter;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
@Singleton
@Component(modules = {AppModule.class, RepositoryModule.class})
public interface AppComponent {
    void injectBoardPresenter(BoardPresenter boardPresenter);

    void injectThreadPresenter(ThreadPresenter threadPresenter);

    void injectMessagePresenter(MessagePresenter messagePresenter);

    @Component.Builder
    interface MyBuilder {
        AppComponent letsBuildThisComponent();

        @BindsInstance
        MyBuilder methodForSettingAppModule(Application application);
    }
}
