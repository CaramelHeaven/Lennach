package com.caramelheaven.lennach.presentation.image_gallery.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class ImageGalleryPresenter extends MvpPresenter<ImageGalleryView> {

    private int currentClickedUserPosition;
    private int listPosition;

    public ImageGalleryPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public int getCurrentClickedUserPosition() {
        return currentClickedUserPosition;
    }

    public void setCurrentClickedUserPosition(int currentClickedUserPosition) {
        this.currentClickedUserPosition = currentClickedUserPosition;
    }

    public int getListPosition() {
        return listPosition;
    }

    public void setListPosition(int listPosition) {
        this.listPosition = listPosition;
    }
}
