package com.caramelheaven.lennach.utils.bus.models;

/**
 * Created by CaramelHeaven on 22:15, 07/12/2018.
 * Pass data to open thread from view pager
 */
public class ActionThread {
    private String board;
    private String threadNumber;

    public ActionThread(String board, String threadNumber) {
        this.board = board;
        this.threadNumber = threadNumber;
    }

    public String getThreadNumber() {
        return threadNumber;
    }

    public String getBoard() {
        return board;
    }
}
