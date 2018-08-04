package com.caramelheaven.lennach.datasource.database.entity;

public class PostFileThread {

    private PostsInThreads postsInThreads;
    private iFile iFile;

    public PostFileThread(PostsInThreads postsInThreads, iFile iFile) {
        this.postsInThreads = postsInThreads;
        this.iFile = iFile;
    }

    public PostsInThreads getPostsInThreads() {
        return postsInThreads;
    }

    public void setPostsInThreads(PostsInThreads postsInThreads) {
        this.postsInThreads = postsInThreads;
    }

    public com.caramelheaven.lennach.datasource.database.entity.iFile getiFile() {
        return iFile;
    }

    public void setiFile(com.caramelheaven.lennach.datasource.database.entity.iFile iFile) {
        this.iFile = iFile;
    }
}
