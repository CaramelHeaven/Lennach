package com.caramelheaven.lennach.domain.thread_use_cases;

import com.caramelheaven.lennach.domain.ThreadRepository;
import com.caramelheaven.lennach.models.model.thread_viewer.Post;

import java.util.List;

import io.reactivex.Single;

public class GetThread {
    private final ThreadRepository threadRepository;

    public GetThread(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    public Single<List<Post>> createUseCase(String boardName, String threadId, String numPost) {
        return threadRepository.getBoard(boardName, threadId, numPost);
    }
}
