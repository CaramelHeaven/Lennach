package com.caramelheaven.lennach.presentation.board_choose.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.domain.board_use_case.GetAllBoard;
import com.caramelheaven.lennach.domain.board_use_case.SaveFavouriteBoards;
import com.caramelheaven.lennach.models.model.board.BoardFavourite;
import com.caramelheaven.lennach.presentation.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 19:25, 13/01/2019.
 */
@InjectViewState
public class BoardChoosePresenter extends BasePresenter<List<BoardFavourite>, BoardChooseView<BoardFavourite>> {

    private boolean allSelected = false;
    private List<BoardFavourite> searchList;

    @Inject
    GetAllBoard getAllBoard;

    @Inject
    SaveFavouriteBoards saveFavouriteBoards;

    public BoardChoosePresenter() {
        super();
        Lennach.getComponentsManager()
                .plusBoardChooseComponent()
                .inject(this);

        searchList = new ArrayList<>();
    }

    @Override
    protected void handlerError(Throwable throwable) {

    }

    @Override
    protected void successfulResult(List<BoardFavourite> result) {
        getViewState().hideProgress();

        if (searchList.isEmpty()) {
            searchList.addAll(result);
        }

        getViewState().updateValues(searchList);
    }

    @Override
    protected void getData() {
        getViewState().showProgress();

        disposable.add(getAllBoard.subscribeToData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerError));
    }

    @Override
    protected void clearData() {
        Lennach.getComponentsManager()
                .clearBoardChooseComponent();
    }

    public void saveBoards(List<BoardFavourite> chosenBoards) {
        saveFavouriteBoards.setData(chosenBoards);

        disposable.add(saveFavouriteBoards.subscribeToData()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().savedData(), throwable -> {
                    Timber.d("th: " + throwable.getMessage());
                }));
    }

    public boolean isAllSelected() {
        return allSelected;
    }

    public void setAllSelected(boolean allSelected) {
        this.allSelected = allSelected;
    }

    public List<BoardFavourite> getSearchList() {
        return searchList;
    }
}
