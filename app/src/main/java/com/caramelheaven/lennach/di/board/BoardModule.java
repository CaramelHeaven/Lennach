package com.caramelheaven.lennach.di.board;

import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.domain.board_use_case.GetBoard;

import dagger.Module;
import dagger.Provides;

@Module
public class BoardModule {
    @BoardScope
    @Provides
    GetBoard provideGetBoard(BoardRepository boardRepository) {
        return new GetBoard(boardRepository);
    }
}
