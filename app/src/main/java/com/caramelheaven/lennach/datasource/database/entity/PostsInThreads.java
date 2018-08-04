package com.caramelheaven.lennach.datasource.database.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Relation;

import java.util.List;

public class PostsInThreads {
    @Embedded
    public iThread iThread;

    @Ignore
    iPost iPost;

    @Relation(entity = iPost.class, parentColumn = "threadId", entityColumn = "idThread")
    public List<iPost> posts;

    @Relation(entity = iFile.class, parentColumn = "postId", entityColumn = "idPost")
    public List<iFile> files;
}
