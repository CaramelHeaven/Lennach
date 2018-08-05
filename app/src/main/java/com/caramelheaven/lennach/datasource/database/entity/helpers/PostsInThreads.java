package com.caramelheaven.lennach.datasource.database.entity.helpers;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.caramelheaven.lennach.datasource.database.entity.iPost;
import com.caramelheaven.lennach.datasource.database.entity.iThread;

import java.util.List;

public class PostsInThreads {
    @Embedded
    public iThread iThread;

    @Relation(entity = iPost.class, parentColumn = "threadId", entityColumn = "idThread")
    public List<iPost> posts;
}
