package com.caramelheaven.lennach.di.board.usenet_list;

import com.caramelheaven.lennach.di.ActivityScope;
import com.caramelheaven.lennach.ui.board.BoardFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = UsenetListModule.class)
public interface UsenetListComponent {
    void inject(BoardFragment boardFragment);
}
