package com.caramelheaven.lennach.di.navigation;

import com.caramelheaven.lennach.domain.BoardSaveRepository;
import com.caramelheaven.lennach.domain.board_use_case.GetFavouriteBoards;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaramelHeaven on 19:36, 13/01/2019.
 */
@Module
public class NavigationModule {
    @Provides
    @NavigationScope
    GetFavouriteBoards provideGetFavouriteBoards(BoardSaveRepository boardSaveRepository) {
        return new GetFavouriteBoards(boardSaveRepository);
    }
}
