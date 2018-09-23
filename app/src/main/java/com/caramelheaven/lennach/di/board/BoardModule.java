package com.caramelheaven.lennach.di.board;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.data.repository.board.BoardLocalRepository;
import com.caramelheaven.lennach.data.repository.board.BoardRemoteRepository;
import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.models.mapper.BoardEntityToBoard;
import com.caramelheaven.lennach.models.mapper.BoardMapper;
import com.caramelheaven.lennach.models.mapper.BoardResponseToBoard;

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
    BoardMapper provideBoardMapper(BoardResponseToBoard boardResponseToBoard,
                                   BoardEntityToBoard boardEntityToBoard) {
        return new BoardMapper(boardResponseToBoard, boardEntityToBoard);
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
}
