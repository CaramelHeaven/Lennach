package com.caramelheaven.lennach.datasource.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * Created by CaramelHeaven on 29.07.2018
 */
@Entity(foreignKeys = @ForeignKey(entity = iBoard.class, parentColumns = "boardId",
        childColumns = "idBoard", onDelete = ForeignKey.CASCADE))
public class iThread {
    @NotNull
    @PrimaryKey
    private String threadId;

    private Integer postsCount;
    private Integer filesCount;

    private String idBoard;

    public iThread(String threadId, Integer postsCount, Integer filesCount, String idBoard) {
        this.threadId = threadId;
        this.postsCount = postsCount;
        this.filesCount = filesCount;
        this.idBoard = idBoard;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    @Override
    public String toString() {
        return "iThread{" +
                "threadId='" + threadId + '\'' +
                ", postsCount=" + postsCount +
                ", filesCount=" + filesCount +
                ", idBoard='" + idBoard + '\'' +
                '}';
    }

    public Integer getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(Integer postsCount) {
        this.postsCount = postsCount;
    }

    public Integer getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(Integer filesCount) {
        this.filesCount = filesCount;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }
}
