package com.caramelheaven.lennach.datasource.database.entity.helpers;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.caramelheaven.lennach.datasource.database.entity.iFile;
import com.caramelheaven.lennach.datasource.database.entity.iPost;

import java.util.List;

public class PostsHelper {
    @Embedded
    public iPost iPost;

    @Relation(entity = iFile.class, parentColumn = "postId", entityColumn = "idPost")
    public List<iFile> iFileList;
}
