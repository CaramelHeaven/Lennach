package com.caramelheaven.lennach.di.board;

import com.caramelheaven.lennach.presentation.board.presenter.BoardPresenter;

import dagger.Subcomponent;

@BoardScope
@Subcomponent(modules = {BoardModule.class})
public interface BoardComponent {
    void inject(BoardPresenter presenter);
}
