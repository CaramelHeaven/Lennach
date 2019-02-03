package com.caramelheaven.lennach.di.board;

import com.caramelheaven.lennach.domain.board_use_cases.GetBoard;
import com.caramelheaven.lennach.domain.repositories.BoardNetworkRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaramelHeaven on 17:20, 03/02/2019.
 */
@Module
public class BoardModule {
    @Provides
    @BoardScope
    GetBoard provideGetBoard(BoardNetworkRepository repository) {
        return new GetBoard(repository);
    }
}
