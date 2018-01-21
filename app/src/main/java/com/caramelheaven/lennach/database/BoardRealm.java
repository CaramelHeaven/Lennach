package com.caramelheaven.lennach.database;

import com.caramelheaven.lennach.data.File;

import java.util.BitSet;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BoardDB extends RealmObject {
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
}
