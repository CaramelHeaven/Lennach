package com.caramelheaven.lennach.presentation.main.saved_history.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.di.thread.thread_favourite.ThreadFavouriteModule;
import com.caramelheaven.lennach.domain.thread_use_cases.GetFavouriteThreads;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;
import com.caramelheaven.lennach.presentation.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class HistoryPresenter extends BasePresenter<HistoryView> {

    @Inject
    GetFavouriteThreads getFavouriteThreads;

    private CompositeDisposable disposable;

    public HistoryPresenter() {
        disposable = new CompositeDisposable();
        Lennach.getComponentsManager()
                .plusThreadComponent()
                .plusThreadFavouriteComponent(new ThreadFavouriteModule())
                .inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Timber.d("HISTORY PRESENTER CREATED");
        handlerHistoryFavourite();
        handlerHistoryRecently();
    }

    private void handlerHistoryFavourite() {
        disposable.add(getFavouriteThreads.provideFavouriteThreads(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResultFavourite, this::handlerError));
    }

    private void handlerHistoryRecently() {
        disposable.add(getFavouriteThreads.provideFavouriteThreads(false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResultRecently, this::handlerError));
    }

    private void successfulResultRecently(List<Usenet> usenets) {
        getViewState().updateRecentlyAdapter(usenets);
    }

    private void successfulResultFavourite(List<Usenet> usenets) {
        getViewState().updateFavouriteAdapter(usenets);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
        Lennach.getComponentsManager()
                .clearThreadComponent();
    }

    @Override
    protected void handlerError(Throwable throwable) {
        Timber.d("error: " + throwable.getMessage());
        Timber.d("error: " + throwable.getCause());
    }
}
