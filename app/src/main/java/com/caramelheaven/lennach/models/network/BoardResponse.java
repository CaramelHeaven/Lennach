package com.caramelheaven.lennach.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BoardResponse {
    @SerializedName("Board")
    @Expose
    private String board;
    @SerializedName("BoardName")
    @Expose
    private String boardName;
    @SerializedName("board_speed")
    @Expose
    private Integer boardSpeed;
    @SerializedName("bump_limit")
    @Expose
    private Integer bumpLimit;
    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("current_thread")
    @Expose
    private Integer currentThread;
    @SerializedName("max_comment")
    @Expose
    private Integer maxComment;
    @SerializedName("max_files_size")
    @Expose
    private Integer maxFilesSize;
    @SerializedName("pages")
    @Expose
    private List<Integer> pages;
    @SerializedName("threads")
    @Expose
    private List<ThreadResponse> threadList;

    public String getBoard() {
        return board;
    }

    public String getBoardName() {
        return boardName;
    }

    public Integer getBoardSpeed() {
        return boardSpeed;
    }

    public Integer getBumpLimit() {
        return bumpLimit;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getCurrentThread() {
        return currentThread;
    }

    public Integer getMaxComment() {
        return maxComment;
    }

    public Integer getMaxFilesSize() {
        return maxFilesSize;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public List<ThreadResponse> getThreadList() {
        return threadList;
    }
}
