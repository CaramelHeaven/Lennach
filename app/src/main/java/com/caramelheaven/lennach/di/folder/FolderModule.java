package com.caramelheaven.lennach.di.folder;

import com.caramelheaven.lennach.domain.board_use_cases.GetFavouriteBoards;
import com.caramelheaven.lennach.domain.repositories.BoardDatabaseRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaramelHeaven on 18:29, 03/02/2019.
 */
@Module
public class FolderModule {
    @Provides
    @FolderScope
    GetFavouriteBoards provideGetFavouriteBoards(BoardDatabaseRepository repository) {
        return new GetFavouriteBoards(repository);
    }
}
