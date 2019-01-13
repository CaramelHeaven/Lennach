package com.caramelheaven.lennach.di.navigation;

import com.caramelheaven.lennach.di.board_choose.BoardChooseComponent;
import com.caramelheaven.lennach.di.board_choose.BoardChooseModule;
import com.caramelheaven.lennach.presentation.navigation.presenter.NavigationPresenter;

import dagger.Subcomponent;

/**
 * Created by CaramelHeaven on 19:36, 13/01/2019.
 */
@NavigationScope
@Subcomponent(modules = NavigationModule.class)
public interface NavigationComponent {

    BoardChooseComponent plusBoardChooseComponent(BoardChooseModule module);

    void inject(NavigationPresenter presenter);
}
