package com.caramelheaven.lennach.datasource.database.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Relation;

import java.util.List;

public class PostsInThreads {
    @Embedded
    public iThread iThread;

    @Relation(entity = iPost.class, parentColumn = "threadId", entityColumn = "idThread")
    public List<iPost> posts;
}
