package com.caramelheaven.lennach.presentation.navigation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.models.model.board.Usenet;
import com.caramelheaven.lennach.models.model.transfer_data.BoardContainer;
import com.caramelheaven.lennach.models.model.common.Delegatable;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

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
        Board board1 = new Board();
        Board board2 = new Board();
        Board board3 = new Board();
        Board board4 = new Board();
        Board board5 = new Board();
        BoardContainer boardContainer = new BoardContainer();
        board.setBoardName("pa");
        boardList.add(board);
        board1.setBoardName("b");
        boardList.add(board1);
        board2.setBoardName("pr");
        boardList.add(board2);
        board3.setBoardName("f");
        boardList.add(board3);
        board4.setBoardName("q");
        boardList.add(board4);
        board5.setBoardName("faq");
        boardList.add(board5);

        Timber.d("boardList init: " + boardList.toString());
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
