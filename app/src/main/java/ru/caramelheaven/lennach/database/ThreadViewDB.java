package ru.caramelheaven.lennach.database;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sergey F on 02.01.2018.
 */

public class ThreadViewDB extends RealmObject implements RealmModel {
    @PrimaryKey
    private String subject;
    private String comment;
    private String date;

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
}