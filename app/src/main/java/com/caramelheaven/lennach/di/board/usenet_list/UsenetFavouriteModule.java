package com.caramelheaven.lennach.di.board.usenet_list;

import com.caramelheaven.lennach.data.repository.board.BoardLocalRepository;
import com.caramelheaven.lennach.domain.board_use_cases.SaveUsenet;

import dagger.Module;
import dagger.Provides;

@Module
public class UsenetFavouriteModule {
    @Provides
    SaveUsenet provideSaveUsenet(BoardLocalRepository boardLocalRepository) {
        return new SaveUsenet(boardLocalRepository);
    }
}
