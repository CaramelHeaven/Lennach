package com.caramelheaven.lennach.models.mapper.board;

import com.caramelheaven.lennach.models.database.BoardEntity;
import com.caramelheaven.lennach.models.mapper.Mapper;
import com.caramelheaven.lennach.models.model.board_viewer.Board;

public class BoardEntityToBoard extends Mapper<Board, BoardEntity> {

    @Override
    public BoardEntity map(Board value) {
        final BoardEntity boardEntity = new BoardEntity();
        fillBoardEntity(boardEntity, value);
        return boardEntity;
    }

    private void fillBoardEntity(BoardEntity boardEntity, Board board) {
        boardEntity.setBoard(board.getBoard());
        boardEntity.setBoardName(board.getBoardName());
        boardEntity.setBoardSpeed(board.getBoardSpeed());
        boardEntity.setBumpLimit(board.getBumpLimit());
        boardEntity.setMaxComment(board.getMaxComment());
        boardEntity.setMaxFileSize(board.getMaxFileSize());
    }

    private void fillBoard(Board board, BoardEntity boardEntity) {
        board.setBoard(boardEntity.getBoard());
        board.setBoardName(boardEntity.getBoardName());
        board.setBoardSpeed(boardEntity.getBoardSpeed());
        board.setBumpLimit(board.getBumpLimit());
        board.setMaxComment(board.getMaxComment());
        board.setMaxFileSize(board.getMaxFileSize());
    }
}
