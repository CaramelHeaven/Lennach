package com.caramelheaven.lennach.models.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "BoardTable")
public class BoardEntity {
    @PrimaryKey
    @ColumnInfo(name = "board")
    private String board;
    @ColumnInfo(name = "board_name")
    private String boardName;
    @ColumnInfo(name = "board_speed")
    private Integer boardSpeed;
    @ColumnInfo(name = "bump_limit")
    private Integer bumpLimit;
    @ColumnInfo(name = "max_comment")
    private Integer maxComment;
    @ColumnInfo(name = "max_file_size")
    private Integer maxFileSize;
    @ColumnInfo(name = "pages")
    private int[] pages;
    @ColumnInfo(name = "current_page")
    private Integer currentPage;

    public BoardEntity() {

    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public Integer getBoardSpeed() {
        return boardSpeed;
    }

    public void setBoardSpeed(Integer boardSpeed) {
        this.boardSpeed = boardSpeed;
    }

    public Integer getBumpLimit() {
        return bumpLimit;
    }

    public void setBumpLimit(Integer bumpLimit) {
        this.bumpLimit = bumpLimit;
    }

    public Integer getMaxComment() {
        return maxComment;
    }

    public void setMaxComment(Integer maxComment) {
        this.maxComment = maxComment;
    }

    public Integer getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Integer maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public int[] getPages() {
        return pages;
    }

    public void setPages(int[] pages) {
        this.pages = pages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
