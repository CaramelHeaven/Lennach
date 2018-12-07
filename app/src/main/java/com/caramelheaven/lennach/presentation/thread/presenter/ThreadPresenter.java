package com.caramelheaven.lennach.presentation.thread.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.presentation.base.BasePresenter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class ThreadPresenter extends BasePresenter<List<Post>, ThreadView> {

    private String boardName;
    private String threadNum;
    private CompositeDisposable disposable;

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
        super.onDestroy();
    }

    @Override
    protected void handlerError(Throwable throwable) {

    }

    @Override
    protected void successfulResult(List<Post> result) {

    }

    @Override
    public void getData() {

    }

    public void setBoardAndThread(String boardName, String threadNum) {
        this.boardName = boardName;
        this.threadNum = threadNum;
    }
}
