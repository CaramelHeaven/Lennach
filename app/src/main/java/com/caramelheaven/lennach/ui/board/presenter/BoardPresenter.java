package com.caramelheaven.lennach.ui.board.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.datasource.database.LennachDatabase;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostFileThread;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostsInThreads;
import com.caramelheaven.lennach.datasource.database.entity.iFile;
import com.caramelheaven.lennach.datasource.database.entity.iPost;
import com.caramelheaven.lennach.datasource.database.entity.iThread;
import com.caramelheaven.lennach.datasource.model.File;
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
    private String boardName;
    private CompositeDisposable disposable = new CompositeDisposable();
    private int totalPage = 0;

    @Inject
    BoardRepository repository;

    @Inject
    LennachDatabase database;

    public BoardPresenter(String boardName) {
        this.boardName = boardName;
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
        loadThreads(boardName, currentPage);
    }

    public void loadThreads(String boardName, int page) {
        if (currentPage == 1) {
            getViewState().showProgress();
        }
        Timber.d("board repository: " + repository.hashCode() + " simple:  " + repository);
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
                        for (Post post : thread.getPosts()) {
                            iPost iPost = new iPost(post.getNum(), post.getBanned(), post.getComment(),
                                    post.getTimestamp(), post.getOp(), post.getDate(), post.getSubject(), thread.getThreadNum());
                            List<iFile> iFiles = new ArrayList<>();
                            for (File file : post.getFiles()) {
                                iFile iFile = new iFile(file.getDisplayname(), file.getDisplayname(),
                                        file.getFullname(), file.getHeight(), file.getWidth(),
                                        file.getPath(), file.getSize(), file.getThumbnail(), post.getNum());
                                iFiles.add(iFile);
                            }
                            database.postDao().insertPost(iPost);
                            database.fileDao().insertFiles(iFiles);
                        }
                        iThreads.add(iThread);
                    }
                    return Single.just(iThreads);
                })
                .flatMap((Function<List<iThread>, SingleSource<List<PostFileThread>>>) iThreads -> {
                    List<PostFileThread> postFileThreads = new ArrayList<>();
                    for (iThread iThread : iThreads) {
                        PostsInThreads postsInThreads = database.threadDao().getPosts(iThread.getThreadId());
                        iFile iFile = database.fileDao().getFileById(postsInThreads.posts.get(0).getPostId());
                        postFileThreads.add(new PostFileThread(postsInThreads, iFile));
                    }
                    return Single.just(postFileThreads);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleLoadingSuccess, this::handleLoadingError));
    }

    private void handleLoadingError(Throwable throwable) {
        //getViewState().showRetryView(throwable.getCause().toString());
    }

    private void handleLoadingSuccess(List<PostFileThread> models) {
        if (currentPage == 1) {
            totalPage = repository.getTotalPage();
            getViewState().hideProgress();
        }
        getViewState().showItems(models);
        isLoading = false;
    }

    public void loadMoreThreads() {
        if (totalPage != currentPage) {
            isLoading = true;
            currentPage++;
            loadThreads("b", currentPage);
        }
    }

    public boolean isLoading() {
        return isLoading;
    }

    public boolean isLastPage() {
        return totalPage == currentPage;
    }
}
