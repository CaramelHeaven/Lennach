package com.caramelheaven.lennach.models.mapper.thread;

import com.caramelheaven.lennach.models.database.UsenetEntity;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;
import com.caramelheaven.lennach.models.model.thread_viewer.Post;
import com.caramelheaven.lennach.models.network.ThreadResponse;

import java.util.List;

public class ThreadMapper {
    private final ThreadResponseToPosts threadResponseToFiber;
    private final UsenetEntityToUsenet usenetEntityToUsenet;

    public ThreadMapper(ThreadResponseToPosts threadResponseToFiber, UsenetEntityToUsenet usenetEntityToUsenet) {
        this.threadResponseToFiber = threadResponseToFiber;
        this.usenetEntityToUsenet = usenetEntityToUsenet;
    }

    public List<Post> map(ThreadResponse threadResponse) {
        return threadResponseToFiber.map(threadResponse);
    }

    public List<Usenet> map(List<UsenetEntity> usenetEntities) {
        return usenetEntityToUsenet.map(usenetEntities);
    }
}
