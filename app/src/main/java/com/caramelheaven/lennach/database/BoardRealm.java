package com.caramelheaven.lennach.database;

import android.os.Parcelable;

import com.caramelheaven.lennach.data.Board;
import com.caramelheaven.lennach.data.File;
import com.caramelheaven.lennach.data.Thread;

import java.io.Serializable;
import java.util.BitSet;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BoardRealm extends RealmObject {
    @PrimaryKey
    private String subject;
    private String comment;
    private String date;
    private RealmList<FileDB> files;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setComment(String name) {
        this.comment = name;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public RealmList<FileDB> getFiles() {
        return files;
    }

    public void setFiles(RealmList<FileDB> files) {
        this.files = files;
    }

    //Потому что доска возвращает список тредов, а треды представлены как коменты, фотки, число и т.д.
    public BoardRealm toEntity() {
        BoardRealm board = new BoardRealm();
        board.subject = subject;
        board.comment = comment;
        board.date = date;
        //thread.files = files;
        return board;
    }

    //Возвращение сущностей в БД
    public void setFromEntity(BoardRealm thread) {
        subject = thread.subject;
        comment = thread.comment;
        date = thread.date;
    }
}
