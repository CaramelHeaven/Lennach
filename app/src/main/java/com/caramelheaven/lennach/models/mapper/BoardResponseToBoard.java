package com.caramelheaven.lennach.models.mapper;

import com.caramelheaven.lennach.models.model.board_viewer.Board;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;
import com.caramelheaven.lennach.models.network.BoardResponse;

import java.util.ArrayList;
import java.util.List;

public class BoardResponseToBoard {

    public Board map(BoardResponse boardResponse) {
        final Board board = new Board();
        if (boardResponse != null) {
            board.setBoard(boardResponse.getBoard());
            board.setBoardName(boardResponse.getBoardName());
            board.setBoardSpeed(boardResponse.getBoardSpeed());
            board.setBumpLimit(boardResponse.getBumpLimit());
            board.setMaxComment(boardResponse.getMaxComment());
            board.setMaxFileSize(boardResponse.getMaxFilesSize());
            List<Usenet> usenets = new ArrayList<>();
            for (int q = 0; q < boardResponse.getThreadList().size(); q++) {
                final Usenet usenet = new Usenet();
                usenet.setFilesCount(boardResponse.getThreadList().get(q).getFilesCount());
                usenet.setPostsCount(boardResponse.getThreadList().get(q).getPostsCount());
                usenet.setThreadNum(boardResponse.getThreadList().get(q).getThreadNum());

                usenet.setDisplayNameImage(boardResponse.getThreadList().get(q)
                        .getPostsList().get(0).getFiles().get(0).getDisplayname());
                usenet.setHeightImage(boardResponse.getThreadList().get(q)
                        .getPostsList().get(0).getFiles().get(0).getHeight());
                usenet.setWidthImage(boardResponse.getThreadList().get(q)
                        .getPostsList().get(0).getFiles().get(0).getWidth());
                usenet.setSizeImage(boardResponse.getThreadList().get(q)
                        .getPostsList().get(0).getFiles().get(0).getSize());
                usenet.setNameImage(boardResponse.getThreadList().get(q)
                        .getPostsList().get(0).getFiles().get(0).getName());
                usenet.setThumbnail(boardResponse.getThreadList().get(q)
                        .getPostsList().get(0).getFiles().get(0).getThumbnail());

                usenet.setComment(boardResponse.getThreadList().get(q)
                        .getPostsList().get(0).getComment());
                usenet.setDate(boardResponse.getThreadList().get(q)
                        .getPostsList().get(0).getDate());
                usenet.setComment(boardResponse.getThreadList().get(q)
                        .getPostsList().get(0).getName());
                usenet.setComment(boardResponse.getThreadList().get(q)
                        .getPostsList().get(0).getNum());

            }
            board.setUsenetList(usenets);
        }
        return board;
    }
}
