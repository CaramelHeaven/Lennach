package com.caramelheaven.lennach.models.model.transfer_data;

import com.caramelheaven.lennach.models.model.board.Board;
import com.caramelheaven.lennach.models.model.common.Delegatable;

import java.util.List;

/**
 * Created by CaramelHeaven on 17:25, 13/01/2019.
 * BoardContainer - class, for helper us to package list of board and show their in
 * the NavigationFragment inside BoardContainerAdapterDelegate
 */
public class BoardContainer implements Delegatable {
    private List<Board> boardList;

    public List<Board> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
    }

    @Override
    public String toString() {
        return "BoardContainer{" +
                "boardList=" + boardList +
                '}';
    }
}
