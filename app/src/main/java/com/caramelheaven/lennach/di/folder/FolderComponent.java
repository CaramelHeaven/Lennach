package com.caramelheaven.lennach.di.folder;

import com.caramelheaven.lennach.presentation.folder.presenter.FolderPresenter;

import dagger.Subcomponent;

/**
 * Created by CaramelHeaven on 18:29, 03/02/2019.
 */
@FolderScope
@Subcomponent(modules = FolderModule.class)
public interface FolderComponent {
    void inject(FolderPresenter presenter);
}
