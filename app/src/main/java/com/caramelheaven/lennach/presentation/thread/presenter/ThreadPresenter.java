package com.caramelheaven.lennach.presentation.thread.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.di.thread.post_list.PostListModule;
import com.caramelheaven.lennach.domain.thread_use_cases.GetThread;
import com.caramelheaven.lennach.models.model.thread_viewer.Post;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ThreadPresenter extends MvpPresenter<ThreadView<Post>> {

    private CompositeDisposable disposable;
    private Set<Post> postUnique;
    private String boardName, threadNum;

    @Inject
    GetThread getThread;

    public ThreadPresenter(String boardName, String threadNum) {
        this.boardName = boardName;
        this.threadNum = threadNum;
        initConstructor();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadPosts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initConstructor() {
        Lennach.getComponentsManager()
                .plusThreadComponent()
                .plusPostListComponent(new PostListModule())
                .inject(this);
        disposable = new CompositeDisposable();
        postUnique = new LinkedHashSet<>();
    }

    private void loadPosts() {
        disposable.add(getThread.createUseCase(boardName, threadNum, "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulLoad, this::unsuccessfulLoad));
    }

    private void successfulLoad(List<Post> posts) {
        getViewState().hideLoading();
        getViewState().showItems(posts);
    }

    private void unsuccessfulLoad(Throwable throwable) {

    }
}
