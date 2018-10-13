package com.caramelheaven.lennach.di.board;

import com.caramelheaven.lennach.di.board.usenet_list.UsenetListComponent;
import com.caramelheaven.lennach.di.board.usenet_list.UsenetListModule;

import dagger.Subcomponent;

@BoardScope
@Subcomponent(modules = BoardModule.class)
public interface BoardComponent {
    UsenetListComponent plusUsenetListComponent(UsenetListModule usenetListModule);
}
