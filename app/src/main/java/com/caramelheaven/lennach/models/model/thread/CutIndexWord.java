package com.caramelheaven.lennach.models.model.thread;

/**
 * Created by CaramelHeaven on 19:15, 09/12/2018.
 * Special class for find answered string in the text of user post and save indexes.
 */
public class CutIndexWord {
    private int startIndex;
    private int endIndex;

    public CutIndexWord(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public String toString() {
        return "CutIndexWord{" +
                "startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                '}';
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}
