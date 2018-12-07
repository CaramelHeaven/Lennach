package com.caramelheaven.lennach.presentation.thread.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.domain.thread_use_case.GetThread;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.presentation.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class ThreadPresenter extends BasePresenter<List<Post>, ThreadView<Post>> {

    private String boardName;
    private String threadNum;
    private CompositeDisposable disposable;

    @Inject
    GetThread getThread;

    public ThreadPresenter(String boardName, String threadNum) {
        this.boardName = boardName;
        this.threadNum = threadNum;
        disposable = new CompositeDisposable();

        Lennach.getComponentsManager()
                .plusThreadComponent()
                .inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        Lennach.getComponentsManager().clearThreadComponent();
        super.onDestroy();
    }

    @Override
    protected void handlerError(Throwable throwable) {
        Timber.d("error: " + throwable.getMessage());
    }

    @Override
    protected void successfulResult(List<Post> result) {
        getViewState().hideProgress();
        getViewState().showItems(result);
    }

    @Override
    public void getData() {
        getViewState().showProgress();
        disposable.add(getThread.subscribeToData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerError));
    }

    public void setBoardAndThread(String boardName, String threadNum) {
        this.boardName = boardName;
        this.threadNum = threadNum;

        Timber.d("setBoardAndThread: " + boardName + " threNum: " + threadNum);
        getThread.setBaseData(boardName, threadNum, "0");
    }
}
