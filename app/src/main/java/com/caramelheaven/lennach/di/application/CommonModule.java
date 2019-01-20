package com.caramelheaven.lennach.di.application;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.data.repository.board.BoardLocalRepository;
import com.caramelheaven.lennach.data.repository.board.BoardRemoteRepository;
import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.models.mapper.board.BoardAllResponseToBoardAll;
import com.caramelheaven.lennach.models.mapper.board.BoardMapper;
import com.caramelheaven.lennach.models.mapper.board.BoardResponseToBoard;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaramelHeaven on 20:44, 20/01/2019.
 * Common module for controlling difference screens and it communication
 */
@Module
public class CommonModule {

    @Provides
    @Singleton
    BoardRepository provideBoardRepository(LennachApiService apiService,
                                           BoardMapper boardMapper) {
        return new BoardRemoteRepository(apiService, boardMapper);
    }

    @Provides
    @Singleton
    BoardLocalRepository provideBoardLocalRepository() {
        return new BoardLocalRepository();
    }

    @Provides
    @Singleton
    BoardMapper provideBoardMapper(BoardResponseToBoard boardResponseToBoard,
                                   BoardAllResponseToBoardAll boardAllResponseToBoardAll) {
        return new BoardMapper(boardResponseToBoard, boardAllResponseToBoardAll);
    }

    @Provides
    @Singleton
    BoardResponseToBoard provideBoardResponseToBoard() {
        return new BoardResponseToBoard();
    }

    @Provides
    @Singleton
    BoardAllResponseToBoardAll provideBoardAllResponseToBoardAll() {
        return new BoardAllResponseToBoardAll();
    }
}
