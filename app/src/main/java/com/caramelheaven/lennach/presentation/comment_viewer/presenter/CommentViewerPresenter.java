package com.caramelheaven.lennach.presentation.comment_viewer.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.presentation.base.BasePresenter;
import com.caramelheaven.lennach.utils.singletons.ThreadFilterPosts;

import java.util.ArrayList;
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
    List<String> listReference;
    private ThreadFilterPosts<Post> threadContainer;
    private CompositeDisposable disposable;

    private String handleData;

    public CommentViewerPresenter(String reference, ArrayList<String> listReference) {
        this.reference = reference;
        this.listReference = listReference;
        disposable = new CompositeDisposable();
        threadContainer = ThreadFilterPosts.getInstance();

        if (reference.equals("")) {
            handleData = "ARRAY";
        } else {
            handleData = "STRING";
        }
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
        switch (handleData) {
            case "STRING":
                disposable.add(threadContainer.filterDateByTextLink(reference)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::successfulResult, this::handlerError));
                break;
            case "ARRAY":
                disposable.add(threadContainer.filterDataByBtnReply(listReference)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::successfulResult, this::handlerError));
                break;
            default:
                //nothing
        }
    }

    @Override
    protected void clearData() {

    }

    @Override
    public void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}
