package com.caramelheaven.lennach.presentation.thread.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.domain.thread_use_case.GetThread;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.presentation.base.BasePresenter;
import com.caramelheaven.lennach.utils.singletons.ThreadFilterPosts;

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

    //set reply and save it if user want's to some manipulate with screen rotate etc.
    private String replyInTheThread;

    //we set new posts for each click of user
    private ThreadFilterPosts<Post> threadContainer;

    /*
     * Button helper for check is more was open.
     */
    private boolean moreOpened = false;

    @Inject
    GetThread getThread;

    public ThreadPresenter(String boardName, String threadNum) {
        this.boardName = boardName;
        this.threadNum = threadNum;
        disposable = new CompositeDisposable();
        threadContainer = ThreadFilterPosts.getInstance();

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
        threadContainer.clearPosts();
        super.onDestroy();
    }

    @Override
    protected void handlerError(Throwable throwable) {
        Timber.d("error: " + throwable.getMessage());
    }

    @Override
    protected void successfulResult(List<Post> result) {
        threadContainer.initPostsFromThread(result);

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

    public boolean isMoreOpened() {
        return moreOpened;
    }

    public void setMoreOpened(boolean moreOpened) {
        this.moreOpened = moreOpened;
    }

    public String getReplyInTheThread() {
        return replyInTheThread;
    }

    public void setReplyInTheThread(String replyInTheThread) {
        this.replyInTheThread = replyInTheThread;
    }
}
