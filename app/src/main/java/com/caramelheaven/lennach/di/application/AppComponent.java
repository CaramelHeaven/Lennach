package com.caramelheaven.lennach.di.application;

import com.caramelheaven.lennach.di.board.BoardComponent;
import com.caramelheaven.lennach.di.board.BoardModule;
import com.caramelheaven.lennach.ui.board.presenter.BoardPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {
    void injectBoardPresenter(BoardPresenter boardPresenter);

    BoardComponent plusBoardComponent(BoardModule boardModule);
}
