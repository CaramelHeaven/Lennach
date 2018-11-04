package com.caramelheaven.lennach.presentation.image_viewer.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.utils.Constants;

import timber.log.Timber;

@InjectViewState
public class ImageViewerPresenter extends MvpPresenter<ImageViewerView> {

    public ImageViewerPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void closeGallery(boolean flag) {
        Timber.d("checking flag from gallery: " + flag);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("DESCTROPED");
        closeGallery(true);
    }
}
