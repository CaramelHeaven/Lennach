package com.caramelheaven.lennach.models.mapper.thread;

import com.caramelheaven.lennach.models.model.thread_viewer.Post;
import com.caramelheaven.lennach.models.network.ThreadResponse;

import java.util.List;

public class ThreadMapper {
    private ThreadResponseToPosts threadResponseToFiber;

    public ThreadMapper(ThreadResponseToPosts threadResponseToFiber) {
        this.threadResponseToFiber = threadResponseToFiber;
    }

    public List<Post> map(ThreadResponse threadResponse) {
        return threadResponseToFiber.map(threadResponse);
    }
}
