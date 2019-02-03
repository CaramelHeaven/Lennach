package com.caramelheaven.lennach.di.main.modules;

import com.caramelheaven.lennach.di.main.MainScope;
import com.caramelheaven.lennach.models.mapper.board.BoardMapper;
import com.caramelheaven.lennach.models.mapper.board.entities.BoardResponseToBoard;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaramelHeaven on 18:00, 03/02/2019.
 */
@Module
public class MapperCoreModule {
    @Provides
    @MainScope
    BoardMapper provideBoardMapper(BoardResponseToBoard boardResponseToBoard) {
        return new BoardMapper(boardResponseToBoard);
    }

    @Provides
    @MainScope
    BoardResponseToBoard provideBoardResponseToBoard() {
        return new BoardResponseToBoard();
    }
}
