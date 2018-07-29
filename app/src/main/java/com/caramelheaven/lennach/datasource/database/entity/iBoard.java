package com.caramelheaven.lennach.datasource.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * Created by CaramelHeaven on 29.07.2018
 */
@Entity
public class iBoard {
    @NotNull
    @PrimaryKey
    private String boardId;

    private String boardName;
    private Integer boardSpeed;
    private Integer bumpLimit;

    public iBoard(){}

    public iBoard(String board, String boardName, Integer boardSpeed, Integer bumpLimit) {
        this.boardId = board;
        this.boardName = boardName;
        this.boardSpeed = boardSpeed;
        this.bumpLimit = bumpLimit;
    }

    @Override
    public String toString() {
        return "iBoard{" +
                "boardId='" + boardId + '\'' +
                ", boardName='" + boardName + '\'' +
                ", boardSpeed=" + boardSpeed +
                ", bumpLimit=" + bumpLimit +
                '}';
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
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
}
