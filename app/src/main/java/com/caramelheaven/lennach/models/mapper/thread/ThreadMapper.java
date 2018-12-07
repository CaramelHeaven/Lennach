package com.caramelheaven.lennach.models.mapper.thread;

import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.models.network.ThreadResponse;

import java.util.List;

/**
 * Created by CaramelHeaven on 00:49, 08/12/2018.
 */
public class ThreadMapper {
    private final ThreadResponseToPosts threadResponseToPosts;

    public ThreadMapper(ThreadResponseToPosts threadResponseToPosts) {
        this.threadResponseToPosts = threadResponseToPosts;
    }

    public List<Post> map(ThreadResponse response) {
        return threadResponseToPosts.map(response);
    }
}
