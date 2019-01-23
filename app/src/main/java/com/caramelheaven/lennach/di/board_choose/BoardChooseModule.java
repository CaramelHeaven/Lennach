package com.caramelheaven.lennach.di.board_choose;

import com.caramelheaven.lennach.domain.BoardRepository;
import com.caramelheaven.lennach.domain.BoardSaveRepository;
import com.caramelheaven.lennach.domain.board_use_case.GetAllBoard;
import com.caramelheaven.lennach.domain.board_use_case.SaveFavouriteBoards;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaramelHeaven on 19:33, 13/01/2019.
 */
@Module
public class BoardChooseModule {
    @BoardChooseScope
    @Provides
    GetAllBoard provideGetAllBoard(BoardRepository boardRepository) {
        return new GetAllBoard(boardRepository);
    }

    @BoardChooseScope
    @Provides
    SaveFavouriteBoards provideSaveFavouriteBoards(BoardSaveRepository boardSaveRepository) {
        return new SaveFavouriteBoards(boardSaveRepository);
    }
}
