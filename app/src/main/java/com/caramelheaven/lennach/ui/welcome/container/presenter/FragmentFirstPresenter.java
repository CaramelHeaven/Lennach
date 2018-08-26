package com.caramelheaven.lennach.ui.welcome.container.presenter;

import android.widget.RelativeLayout;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.ui.welcome.container.view.FirstView;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

@InjectViewState
public class FragmentFirstPresenter extends MvpPresenter<FirstView> {

    List<RelativeLayout> wbContainer;

    public FragmentFirstPresenter() {
        wbContainer = new ArrayList<>();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Timber.d("Chkecing load");
    }

    public void savedItems(List<RelativeLayout> models) {
        wbContainer.clear();
        wbContainer.addAll(models);
    }

    public void showSavedItems() {
        if (wbContainer.size() != 0) {
            getViewState().showItems(wbContainer);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
