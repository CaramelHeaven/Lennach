package com.caramelheaven.lennach.di.main;

import com.caramelheaven.lennach.di.board.BoardComponent;
import com.caramelheaven.lennach.di.board.BoardModule;
import com.caramelheaven.lennach.di.folder.FolderComponent;
import com.caramelheaven.lennach.di.folder.FolderModule;
import com.caramelheaven.lennach.di.main.modules.BoardCoreModule;
import com.caramelheaven.lennach.di.main.modules.MapperCoreModule;
import com.caramelheaven.lennach.di.main.modules.ThreadCoreModule;
import com.caramelheaven.lennach.presentation.main.presenter.MainPresenter;

import dagger.Subcomponent;

/**
 * Created by CaramelHeaven on 18:00, 03/02/2019.
 */

@MainScope
@Subcomponent(modules = {BoardCoreModule.class, MapperCoreModule.class, ThreadCoreModule.class, MainModule.class})
public interface MainComponent {
    BoardComponent plusBoardComponent(BoardModule boardModule);

    FolderComponent plusFolderComponent(FolderModule folderModule);

    void inject(MainPresenter presenter);
}
