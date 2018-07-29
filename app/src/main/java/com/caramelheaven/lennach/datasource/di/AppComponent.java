package com.caramelheaven.lennach.datasource.di;

import android.app.Application;

import com.caramelheaven.lennach.datasource.di.module.AppModule;
import com.caramelheaven.lennach.datasource.di.module.RepositoryModule;
import com.caramelheaven.lennach.ui.board.presenter.BoardPresenter;

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

    @Component.Builder
    interface MyBuilder {
        AppComponent letsBuildThisComponent();

        @BindsInstance
        MyBuilder methodForSettingAppModule(Application application);
    }
}
