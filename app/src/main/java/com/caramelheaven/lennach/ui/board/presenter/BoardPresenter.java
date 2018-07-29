package com.caramelheaven.lennach.ui.board.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.datasource.database.LennachDatabase;
import com.caramelheaven.lennach.datasource.database.entity.iBoard;
import com.caramelheaven.lennach.datasource.database.entity.iPost;
import com.caramelheaven.lennach.datasource.database.entity.iThread;
import com.caramelheaven.lennach.datasource.model.Post;
import com.caramelheaven.lennach.datasource.model.Thread;
import com.caramelheaven.lennach.datasource.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 27.07.2018
 */
@InjectViewState
public class BoardPresenter extends MvpPresenter<BoardView> {

    private int currentPage = 1;
    private boolean isLoading = false;
    private boolean isEndPage = false;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    BoardRepository repository;

    @Inject
    LennachDatabase database;

    public BoardPresenter() {
        Lennach.getComponent().injectBoardPresenter(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        initLoad();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    public void initLoad() {
        loadThreads("b", currentPage);
    }

    public void loadThreads(String boardName, int page) {
        if (currentPage == 1) {
            getViewState().showProgress();
        }
        disposable.add(repository.getBoardByName(boardName, page)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(d -> {
                    isLoading = true;
                })
                .flatMap((Function<List<Thread>, SingleSource<List<iThread>>>) threads -> {
                    List<iThread> iThreads = new ArrayList<>();
                    for (Thread thread : threads) {
                        iThread iThread = new iThread(thread.getThreadNum(), thread.getPostsCount(), thread.getFilesCount(), boardName);
                        database.threadDao().insertThread(iThread);
                        List<iPost> iPosts = new ArrayList<>();
                        for (Post post : thread.getPosts()) {
                            iPost iPost = new iPost(post.getNum(), post.getBanned(), post.getComment(), post.getTimestamp(), post.getOp(), post.getDate(), thread.getThreadNum());
                            iPosts.add(iPost);
                        }
                        database.postDao().insertPost(iPosts);
                        iThreads.add(iThread);
                    }
                    return Single.just(iThreads);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleLoadingSuccess, this::handleLoadingError));
    }

    private void handleLoadingError(Throwable throwable) {
        getViewState().showRetryView(throwable.getCause().toString());
    }

    private void handleLoadingSuccess(List<iThread> models) {
        if (currentPage == 1) {
            getViewState().hideProgress();
        }
        for (iThread iThread : models) {
            Timber.d("handleLoadingSuccess: " + iThread.toString());
        }
        getViewState().showItems(models);
        currentPage++;
    }
}
