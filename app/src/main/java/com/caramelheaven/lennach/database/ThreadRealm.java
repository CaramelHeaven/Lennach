package com.caramelheaven.lennach.database;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class ThreadRealm extends RealmObject {

    @PrimaryKey
    @Required
    private String date;

    private Integer number; //user number inside thread
    private String subject;
    private String name;
    private String comment;
    private RealmList<FileDB> files;//images

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

    public RealmList<FileDB> getFiles() {
        return files;
    }

    public void setFiles(RealmList<FileDB> files) {
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ThreadRealm toEntityThread() {
        ThreadRealm thread = new ThreadRealm();
        thread.number = number;
        thread.subject = subject;
        thread.comment = comment;
        thread.date = date;
        thread.files = files;
        return thread;
    }

    public void setFromEntityThread(ThreadRealm thread) {
        number = thread.number;
        subject = thread.subject;
        comment = thread.comment;
        date = thread.date;
        files = thread.files;
    }
}
