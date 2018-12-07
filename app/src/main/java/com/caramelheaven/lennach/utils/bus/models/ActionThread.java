package com.caramelheaven.lennach.utils.bus.models;

/**
 * Created by CaramelHeaven on 22:15, 07/12/2018.
 * Pass data to open thread from view pager
 */
public class ActionThread {
    private String threadNumber;
    private String board;

    public ActionThread(String threadNumber, String board) {
        this.threadNumber = threadNumber;
        this.board = board;
    }

    public String getThreadNumber() {
        return threadNumber;
    }

    public String getBoard() {
        return board;
    }
}
