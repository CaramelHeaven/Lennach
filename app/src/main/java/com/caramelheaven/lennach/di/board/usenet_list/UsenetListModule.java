package com.caramelheaven.lennach.di.board.usenet_list;

import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.domain.board_use_cases.GetBoard;

import dagger.Module;

@Module
public class UsenetListModule {

    GetBoard provideGetBoard(BoardRepository boardRepository) {
        return new GetBoard(boardRepository);
    }
}