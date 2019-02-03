package com.caramelheaven.lennach.di.main.modules;

import com.caramelheaven.lennach.data.datasource.database.dao.BoardDao;
import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.data.repository.board.BoardLocalRepository;
import com.caramelheaven.lennach.data.repository.board.BoardRemoteRepository;
import com.caramelheaven.lennach.di.main.MainScope;
import com.caramelheaven.lennach.domain.repositories.BoardDatabaseRepository;
import com.caramelheaven.lennach.domain.repositories.BoardNetworkRepository;
import com.caramelheaven.lennach.models.mapper.board.BoardMapper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaramelHeaven on 18:00, 03/02/2019.
 */
@Module
public class BoardCoreModule {
    @Provides
    @MainScope
    BoardNetworkRepository provideBoardNetworkRepository(LennachApiService apiService,
                                                         BoardMapper boardMapper) {
        return new BoardRemoteRepository(apiService, boardMapper);
    }

    @Provides
    @MainScope
    BoardDatabaseRepository provideBoardDatabaseRepository(BoardDao boardDao, BoardMapper boardMapper) {
        return new BoardLocalRepository(boardDao, boardMapper);
    }
}
