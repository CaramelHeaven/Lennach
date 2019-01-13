package com.caramelheaven.lennach.presentation.navigation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.models.model.board.Usenet;
import com.caramelheaven.lennach.models.model.transfer_data.BoardContainer;
import com.caramelheaven.lennach.models.model.common.Delegatable;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class NavigationPresenter extends MvpPresenter<NavigationView> {

    public NavigationPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().showFavouriteData(dataUsenets());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private List<Board> databoard() {
        List<Board> boards = new ArrayList<>();
        boards.add(new Board());
        boards.add(new Board());
        boards.add(new Board());
        boards.add(new Board());

        return boards;
    }

    private List<Delegatable> dataUsenets() {
        List<Delegatable> usenets = new ArrayList();
        List<Board> boardList = new ArrayList<>();
        Board board = new Board();
        BoardContainer boardContainer = new BoardContainer();

        board.setBoardName("pa");
        boardList.add(board);
        board.setBoardName("b");
        boardList.add(board);
        board.setBoardName("pr");
        boardList.add(board);

        boardContainer.setBoardList(boardList);
        usenets.add(boardContainer);

        Usenet usenet = new Usenet();
        usenet.setNum("1");
        usenets.add(usenet);
        usenet.setNum("12");
        usenets.add(usenet);
        usenet.setNum("13");
        usenets.add(usenet);
        usenet.setNum("14");
        usenets.add(usenet);

        return usenets;
    }
}
