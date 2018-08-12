package com.caramelheaven.lennach.ui.main.navigation.add.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.datasource.model.BoardNavModel;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

@InjectViewState
public class AddDialogPresenter extends MvpPresenter<AddDialogView> {

    private Set<Integer> selectedPositions;

    public AddDialogPresenter() {
        Timber.d("Add dialog constructor");
        selectedPositions = new LinkedHashSet<>();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Timber.d("onFirstViewAttach ");
        onLoadData();
    }

    public void onLoadData() {
        List<BoardNavModel> iBoardNavs = new ArrayList<>();
        iBoardNavs.add(new BoardNavModel("FirstName"));
        iBoardNavs.add(new BoardNavModel("SecondName"));
        iBoardNavs.add(new BoardNavModel("ThirdName"));
        getViewState().showItems(iBoardNavs);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void saveSelectedPos(Set<Integer> selectedPositions) {
        this.selectedPositions.addAll(selectedPositions);
    }

    public void getSaveSelectedPos() {
        Timber.d("isEmplty: " + selectedPositions.isEmpty());
        if (!selectedPositions.isEmpty()) {
            getViewState().showSelectedItems(selectedPositions);
        }
    }
}
