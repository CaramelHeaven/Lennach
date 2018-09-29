package com.caramelheaven.lennach.domain;

import com.caramelheaven.lennach.models.model.thread_viewer.Post;

import java.util.List;

import io.reactivex.Single;

public interface ThreadRepository {
    Single<List<Post>> getBoard(String boardName, String threadId, String numPost);
}
