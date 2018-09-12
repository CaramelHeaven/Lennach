package com.caramelheaven.lennach.ui.thread.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.datasource.database.LennachDatabase;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostsHelper;
import com.caramelheaven.lennach.datasource.database.entity.iFile;
import com.caramelheaven.lennach.datasource.database.entity.iPost;
import com.caramelheaven.lennach.datasource.model.File;
import com.caramelheaven.lennach.datasource.model.Post;
import com.caramelheaven.lennach.datasource.network.ApiService;
import com.caramelheaven.lennach.datasource.repository.ThreadRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ThreadPresenter extends MvpPresenter<ThreadView> {

    private String idThread;
    private String boardName;
    private CompositeDisposable disposable;

    @Inject
    ThreadRepository repository;

    @Inject
    ApiService apiService;

    @Inject
    LennachDatabase database;

    public ThreadPresenter(String boardName, String idThread) {
        this.boardName = boardName;
        this.idThread = idThread;
        disposable = new CompositeDisposable();
        Lennach.getComponent().injectThreadPresenter(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadPosts();
    }

    public void loadPosts() {
        getViewState().showProgress();
        disposable.add(apiService.getPostsByThreadId(boardName, idThread, idThread)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(posts -> {
                    getViewState().hideProgress();
                    getViewState().showItems(posts);
                }));
    }

//    public void getPosts(String threadNumber) {
//
//        disposable.add(repository.getPostsFromDatabase(threadNumber)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(posts -> {
//                    getViewState().hideProgress();
//                    getViewState().showItems(posts);
//                }));
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        repository.clearRequests();
        disposable.clear();
    }
}
