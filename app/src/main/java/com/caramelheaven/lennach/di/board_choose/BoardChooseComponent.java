package com.caramelheaven.lennach.di.board_choose;

import com.caramelheaven.lennach.presentation.board_choose.presenter.BoardChoosePresenter;

import dagger.Subcomponent;

/**
 * Created by CaramelHeaven on 19:33, 13/01/2019.
 */
@BoardChooseScope
@Subcomponent(modules = BoardChooseModule.class)
public interface BoardChooseComponent {
    void inject(BoardChoosePresenter boardChoosePresenter);
}
