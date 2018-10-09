package com.caramelheaven.lennach.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ThreadResponse {
    @SerializedName("files_count")
    @Expose
    private Integer filesCount;
    @SerializedName("posts")
    @Expose
    private List<PostResponse> postsList;
    @SerializedName("posts_count")
    @Expose
    private Integer postsCount;
    @SerializedName("thread_num")
    @Expose
    private String threadNum;

    public Integer getFilesCount() {
        return filesCount;
    }

    public List<PostResponse> getPostsList() {
        return postsList;
    }

    public Integer getPostsCount() {
        return postsCount;
    }

    public String getThreadNum() {
        return threadNum;
    }
}
