package com.caramelheaven.lennach.di.board;

import android.content.Context;

import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.data.datasource.database.ThreadDao;
import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.data.repository.board.BoardLocalRepository;
import com.caramelheaven.lennach.data.repository.board.BoardRemoteRepository;
import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.models.mapper.board.BoardEntityToBoard;
import com.caramelheaven.lennach.models.mapper.board.BoardMapper;
import com.caramelheaven.lennach.models.mapper.board.BoardResponseToBoard;
import com.caramelheaven.lennach.models.mapper.board.UsenetToUsenetEntity;

import dagger.Module;
import dagger.Provides;

@Module
public class BoardModule {

    @BoardScope
    @Provides
    BoardRepository boardRepository(BoardLocalRepository local,
                                    LennachApiService apiService,
                                    BoardMapper boardMapper) {
        return new BoardRemoteRepository(local, apiService, boardMapper);
    }

    @BoardScope
    @Provides
    BoardLocalRepository provideBoardLocalRepository(BoardMapper threadMapper,
                                                     ThreadDao threadDao) {
        return new BoardLocalRepository(threadMapper, threadDao);
    }

    @BoardScope
    @Provides
    ThreadDao provideThreadDao(Context context) {
        Lennach lennach = (Lennach) context;
        return lennach.getDatabase().getUsenetDao();
    }

    @BoardScope
    @Provides
    BoardMapper provideBoardMapper(BoardResponseToBoard boardResponseToBoard,
                                   BoardEntityToBoard boardEntityToBoard,
                                   UsenetToUsenetEntity usenetToUsenetEntity) {
        return new BoardMapper(boardResponseToBoard, boardEntityToBoard, usenetToUsenetEntity);
    }

    @BoardScope
    @Provides
    BoardEntityToBoard provideBoardEntityToBoard() {
        return new BoardEntityToBoard();
    }

    @BoardScope
    @Provides
    BoardResponseToBoard provideBoardResponseToBoard() {
        return new BoardResponseToBoard();
    }

    @BoardScope
    @Provides
    UsenetToUsenetEntity provideUsenetToUsenetEntity() {
        return new UsenetToUsenetEntity();
    }
}
