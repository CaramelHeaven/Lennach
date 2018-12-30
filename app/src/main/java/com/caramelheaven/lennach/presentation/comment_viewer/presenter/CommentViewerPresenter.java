package com.caramelheaven.lennach.presentation.comment_viewer.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.presentation.base.BasePresenter;
import com.caramelheaven.lennach.utils.singletons.ThreadFilterPosts;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 16:53, 15/12/2018.
 */
@InjectViewState
public class CommentViewerPresenter extends BasePresenter<List<Post>, CommentViewerView<Post>> {

    private String reference;
    private ThreadFilterPosts threadContainer;
    private CompositeDisposable disposable;

    public CommentViewerPresenter(String reference) {
        this.reference = reference;
        disposable = new CompositeDisposable();
        threadContainer = ThreadFilterPosts.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

    }

    @Override
    protected void handlerError(Throwable throwable) {

    }

    @Override
    protected void successfulResult(List<Post> result) {
        Timber.d("successful : " + result.size());
        getViewState().hideProgress();
        getViewState().onShowComments(result);
    }

    @Override
    protected void getData() {
        getViewState().showProgress();
        disposable.add(threadContainer.filteringDataByReference(reference)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerError));
    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}
