package com.caramelheaven.lennach.ui.thread.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostsHelper;
import com.caramelheaven.lennach.datasource.repository.ThreadRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ThreadPresenter extends MvpPresenter<ThreadView> {

    private String idThread;
    private String boardName;
    private CompositeDisposable disposable;

    @Inject
    ThreadRepository repository;

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
        repository.getPosts(boardName, idThread, idThread);
    }


    public void getPosts(String threadNumber) {
        disposable.add(repository.getPostsFromDatabase(threadNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(posts -> {
                    getViewState().hideProgress();
                    getViewState().showItems(posts);
                }));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        repository.clearRequests();
        disposable.clear();
    }
}
