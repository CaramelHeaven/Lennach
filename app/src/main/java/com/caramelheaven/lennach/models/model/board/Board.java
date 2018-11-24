package com.caramelheaven.lennach.models.model.board;

import java.util.List;
import java.util.Objects;

public class Board {
    private String board;
    private String boardName;
    private Integer boardSpeed;
    private Integer bumpLimit;
    private Integer maxComment;
    private Integer maxFileSize;
    private List<Integer> pages;
    private Integer currentPage;
    private List<Usenet> usenetList;

    @Override
    public String toString() {
        return "Board{" +
                "board='" + board + '\'' +
                ", boardName='" + boardName + '\'' +
                ", boardSpeed=" + boardSpeed +
                ", bumpLimit=" + bumpLimit +
                ", maxComment=" + maxComment +
                ", maxFileSize=" + maxFileSize +
                ", pages=" + pages +
                ", currentPage=" + currentPage +
                ", usenetList=" + usenetList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return Objects.equals(board, board1.board) &&
                Objects.equals(boardName, board1.boardName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, boardName);
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public String getBoard() {
        return board;
    }

    public String getBoardName() {
        return boardName;
    }

    public Integer getBoardSpeed() {
        return boardSpeed;
    }

    public Integer getBumpLimit() {
        return bumpLimit;
    }

    public Integer getMaxComment() {
        return maxComment;
    }

    public Integer getMaxFileSize() {
        return maxFileSize;
    }

    public List<Usenet> getUsenetList() {
        return usenetList;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public void setBoardSpeed(Integer boardSpeed) {
        this.boardSpeed = boardSpeed;
    }

    public void setBumpLimit(Integer bumpLimit) {
        this.bumpLimit = bumpLimit;
    }

    public void setMaxComment(Integer maxComment) {
        this.maxComment = maxComment;
    }

    public void setMaxFileSize(Integer maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public void setUsenetList(List<Usenet> usenetList) {
        this.usenetList = usenetList;
    }
}
