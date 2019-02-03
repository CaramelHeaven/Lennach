package com.caramelheaven.lennach.di.board;

/**
 * Created by CaramelHeaven on 17:20, 03/02/2019.
 */

import com.caramelheaven.lennach.presentation.board.presenter.BoardPresenter;

import dagger.Subcomponent;

@BoardScope
@Subcomponent(modules = BoardModule.class)
public interface BoardComponent {
    void inject(BoardPresenter presenter);
}
