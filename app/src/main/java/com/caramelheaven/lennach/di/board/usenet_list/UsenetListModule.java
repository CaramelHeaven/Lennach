package com.caramelheaven.lennach.di.board.usenet_list;

import com.caramelheaven.lennach.di.ActivityScope;
import com.caramelheaven.lennach.di.board.BoardScope;
import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.domain.board_use_cases.GetBoard;

import dagger.Module;
import dagger.Provides;

@Module
public class UsenetListModule {

    @Provides
    @ActivityScope
    GetBoard provideGetBoard(BoardRepository boardRepository) {
        return new GetBoard(boardRepository);
    }
}