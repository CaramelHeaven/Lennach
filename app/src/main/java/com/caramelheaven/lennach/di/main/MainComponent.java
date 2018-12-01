package com.caramelheaven.lennach.di.main;

import com.caramelheaven.lennach.di.board.BoardComponent;
import com.caramelheaven.lennach.di.board.BoardModule;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;

import dagger.Subcomponent;

@MainScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent {

    BoardComponent plusBoardComponent(BoardModule module);

    void inject(MainPresenter mainPresenter);
}
