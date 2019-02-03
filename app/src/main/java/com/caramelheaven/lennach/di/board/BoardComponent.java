package com.caramelheaven.lennach.di.board;

import com.caramelheaven.lennach.presentation.board.presenter.BoardPresenter;

import dagger.Subcomponent;

/**
 * Created by CaramelHeaven on 18:10, 03/02/2019.
 */
@BoardScope
@Subcomponent(modules = {BoardModule.class})
public interface BoardComponent {
    void inject(BoardPresenter presenter);
}
