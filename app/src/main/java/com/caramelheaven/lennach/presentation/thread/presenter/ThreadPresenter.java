package com.caramelheaven.lennach.presentation.thread.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.presentation.base.BasePresenter;

import java.util.List;

@InjectViewState
public class ThreadPresenter extends BasePresenter<List<Post>, ThreadView> {

    private String boardName;
    private Post mainPost;

    public ThreadPresenter(String boardName, Post mainPost) {
        this.boardName = boardName;
        this.mainPost = mainPost;
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
    protected void getData() {

    }

    private String getThreadNumber() {
        return mainPost.getNum();
    }

    public Post getMainPost() {
        return mainPost;
    }
}
