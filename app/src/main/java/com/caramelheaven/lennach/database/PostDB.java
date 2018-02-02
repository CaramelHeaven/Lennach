package com.caramelheaven.lennach.database;

import io.realm.RealmList;
import io.realm.RealmObject;

public class PostDB extends RealmObject {

    private String name;
    private Integer num;
    private Integer number; //user number inside thread
    private Integer op;
    private String parent;
    private Integer sticky;
    private String subject;
    private String tags;
    private Integer timestamp;
    private String trip;

    //private Integer banned;
    //private Integer closed;
    private String comment;
    private String date;

    //private String email
    //private Integer endless;
    private RealmList<FileDB> files;//images
    private Integer lasthit;

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

    public Integer getLasthit() {
        return lasthit;
    }

    public void setLasthit(Integer lasthit) {
        this.lasthit = lasthit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getOp() {
        return op;
    }

    public void setOp(Integer op) {
        this.op = op;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getSticky() {
        return sticky;
    }

    public void setSticky(Integer sticky) {
        this.sticky = sticky;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }
}
