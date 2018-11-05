package com.caramelheaven.lennach.models.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "UsenetTable")
public class UsenetEntity {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "thread_num")
    private String threadNum;

    //info
    @ColumnInfo(name = "files_count")
    private Integer filesCount;
    @ColumnInfo(name = "posts_count")
    private Integer postsCount;

    //post
    @ColumnInfo(name = "comment")
    private String comment;
    @ColumnInfo(name = "date")
    private String date;

    //favourite
    @ColumnInfo(name = "favourite")
    private Boolean favourite;

    public UsenetEntity() {

    }

    @Override
    public String toString() {
        return "UsenetEntity{" +
                "threadNum='" + threadNum + '\'' +
                ", filesCount=" + filesCount +
                ", postsCount=" + postsCount +
                ", comment='" + comment + '\'' +
                ", date='" + date + '\'' +
                ", favourite=" + favourite +
                '}';
    }

    @NonNull
    public String getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(String threadNum) {
        this.threadNum = threadNum;
    }

    public Integer getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(Integer filesCount) {
        this.filesCount = filesCount;
    }

    public Integer getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(Integer postsCount) {
        this.postsCount = postsCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public Boolean isFavourite() {
        return favourite;
    }
}
