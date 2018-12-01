package com.caramelheaven.lennach.di.board;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.data.repository.board.BoardLocalRepository;
import com.caramelheaven.lennach.data.repository.board.BoardRemoteRepository;
import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.domain.board_use_case.GetBoard;
import com.caramelheaven.lennach.models.mapper.board.BoardMapper;
import com.caramelheaven.lennach.models.mapper.board.BoardResponseToBoard;

import dagger.Module;
import dagger.Provides;

@Module
public class BoardModule {
    @BoardScope
    @Provides
    GetBoard provideGetBoard(BoardRepository boardRepository) {
        return new GetBoard(boardRepository);
    }

    @BoardScope
    @Provides
    BoardRepository boardRepository(LennachApiService apiService,
                                    BoardMapper boardMapper) {
        return new BoardRemoteRepository(apiService, boardMapper);
    }

    @BoardScope
    @Provides
    BoardLocalRepository provideBoardLocalRepository() {
        return new BoardLocalRepository();
    }

    @BoardScope
    @Provides
    BoardMapper provideBoardMapper(BoardResponseToBoard boardResponseToBoard) {
        return new BoardMapper(boardResponseToBoard);
    }

    @BoardScope
    @Provides
    BoardResponseToBoard provideBoardResponseToBoard() {
        return new BoardResponseToBoard();
    }
}
