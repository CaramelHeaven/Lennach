package com.caramelheaven.lennach.di.board;

import dagger.Subcomponent;

@BoardScope
@Subcomponent(modules = {BoardModule.class})
public interface BoardComponent {
}
