package com.caramelheaven.lennach.di.application;

import com.caramelheaven.lennach.data.datasource.database.dao.BoardDao;
import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.data.repository.board.BoardLocalRepository;
import com.caramelheaven.lennach.data.repository.board.BoardRemoteRepository;
import com.caramelheaven.lennach.data.repository.thread.ThreadRemoteRepository;
import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.domain.BoardSaveRepository;
import com.caramelheaven.lennach.domain.ThreadRepository;
import com.caramelheaven.lennach.models.mapper.board.BoardAllResponseToBoardAll;
import com.caramelheaven.lennach.models.mapper.board.BoardAllToBoardFavouriteDb;
import com.caramelheaven.lennach.models.mapper.board.BoardFavouriteDbToBoardFavourite;
import com.caramelheaven.lennach.models.mapper.board.BoardMapper;
import com.caramelheaven.lennach.models.mapper.board.BoardResponseToBoard;
import com.caramelheaven.lennach.models.mapper.thread.ThreadMapper;
import com.caramelheaven.lennach.models.mapper.thread.ThreadResponseToPosts;

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
    BoardSaveRepository provideBoardSaveRepository(BoardMapper boardMapper, BoardDao boardDao) {
        return new BoardLocalRepository(boardMapper, boardDao);
    }

    @Provides
    @Singleton
    ThreadRepository provideThreadRepository(LennachApiService apiService, ThreadMapper threadMapper) {
        return new ThreadRemoteRepository(apiService, threadMapper);
    }

    @Provides
    @Singleton
    ThreadMapper provideThreadMapper(ThreadResponseToPosts threadResponseToPosts) {
        return new ThreadMapper(threadResponseToPosts);
    }

    @Provides
    @Singleton
    BoardMapper provideBoardMapper(BoardResponseToBoard boardResponseToBoard,
                                   BoardAllResponseToBoardAll boardAllResponseToBoardAll,
                                   BoardAllToBoardFavouriteDb boardAllToBoardFavouriteDb,
                                   BoardFavouriteDbToBoardFavourite boardFavouriteDbToBoardFavourite) {
        return new BoardMapper(boardResponseToBoard, boardAllResponseToBoardAll,
                boardAllToBoardFavouriteDb, boardFavouriteDbToBoardFavourite);
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

    @Provides
    @Singleton
    BoardAllToBoardFavouriteDb provideBoardAllToBoardFavouriteDb() {
        return new BoardAllToBoardFavouriteDb();
    }

    @Provides
    @Singleton
    BoardFavouriteDbToBoardFavourite provideBoardFavouriteDbToBoardFavourite() {
        return new BoardFavouriteDbToBoardFavourite();
    }

    @Provides
    @Singleton
    ThreadResponseToPosts provideThreadResponseToPosts() {
        return new ThreadResponseToPosts();
    }
}
