package com.caramelheaven.lennach.presentation.folder.presenter;

import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.domain.board_use_cases.GetFavouriteBoards;
import com.caramelheaven.lennach.presentation.base.presenter.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 18:37, 03/02/2019.
 */
public class FolderPresenter extends BasePresenter<FolderView> {

    @Inject
    GetFavouriteBoards getFavouriteBoards;

    public FolderPresenter() {
        super();
        Lennach.getComponentsManager()
                .plusFolderComponent()
                .inject(this);
    }

    @Override
    protected void baseHandlerRepository() {
        disposable.add(getFavouriteBoards.handler()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Timber.d("result: " + result.toString());
                }));
    }
}
