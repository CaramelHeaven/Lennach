package com.caramelheaven.lennach.models.model.thread;

import java.util.List;

/**
 * Created by CaramelHeaven on 22:43, 11/12/2018.
 * This is subcomment which allow us grab something replies and connect it to one answer like below:
 * >>30303030
 * >>29929229
 * >>33030000
 * I will answered.
 */
public class SubComment {
    private List<String> repliesList;
    private String comment;

    public SubComment(List<String> repliesList, String comment) {
        this.repliesList = repliesList;
        this.comment = comment;
    }

    public List<String> getRepliesList() {
        return repliesList;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "SubComment{" +
                "repliesList=" + repliesList +
                ", comment='" + comment + '\'' +
                '}';
    }
}
