package com.caramelheaven.lennach.datasource.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * Created by CaramelHeaven on 29.07.2018
 */
@Entity(foreignKeys = @ForeignKey(entity = iThread.class, parentColumns = "threadId",
        childColumns = "idThread", onDelete = ForeignKey.CASCADE))
public class iPost {
    @NotNull
    @PrimaryKey
    private String postId;

    private Integer banned;
    private String comment;
    private Integer timestamp;
    private Integer op;
    private String date;

    private String idThread;

    public iPost(String postId, Integer banned, String comment, Integer timestamp, Integer op, String date, String idThread) {
        this.postId = postId;
        this.banned = banned;
        this.comment = comment;
        this.timestamp = timestamp;
        this.op = op;
        this.date = date;
        this.idThread = idThread;
    }

    @Override
    public String toString() {
        return "iPost{" +
                "postId='" + postId + '\'' +
                ", banned=" + banned +
                ", comment='" + comment + '\'' +
                ", timestamp=" + timestamp +
                ", op=" + op +
                ", date='" + date + '\'' +
                ", idThread='" + idThread + '\'' +
                '}';
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Integer getBanned() {
        return banned;
    }

    public void setBanned(Integer banned) {
        this.banned = banned;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getOp() {
        return op;
    }

    public void setOp(Integer op) {
        this.op = op;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdThread() {
        return idThread;
    }

    public void setIdThread(String idThread) {
        this.idThread = idThread;
    }
}
