package com.caramelheaven.lennach.di.board.usenet_list;

import com.caramelheaven.lennach.di.ActivityScope;
import com.caramelheaven.lennach.presentation.board.presenter.BoardPresenter;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {UsenetListModule.class, UsenetFavouriteModule.class})
public interface UsenetListComponent {
    void inject(BoardPresenter boardPresenter);
}
