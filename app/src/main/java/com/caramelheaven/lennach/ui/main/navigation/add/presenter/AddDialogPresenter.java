package com.caramelheaven.lennach.ui.main.navigation.add.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.datasource.model.BoardNavModel;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class AddDialogPresenter extends MvpPresenter<AddDialogView> {

    public AddDialogPresenter() {

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
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
}
