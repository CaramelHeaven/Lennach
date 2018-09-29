package com.caramelheaven.lennach.data.repository.thread;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.domain.ThreadRepository;
import com.caramelheaven.lennach.models.mapper.thread.ThreadMapper;
import com.caramelheaven.lennach.models.model.thread_viewer.Post;

import java.util.List;

import io.reactivex.Single;

public class ThreadRemoteRepository implements ThreadRepository {

    private final LennachApiService apiService;
    private final ThreadMapper threadMapper;

    public ThreadRemoteRepository(LennachApiService apiService, ThreadMapper threadMapper) {
        this.apiService = apiService;
        this.threadMapper = threadMapper;
    }

    @Override
    public Single<List<Post>> getBoard(String boardName, String threadId, String numPost) {
        return apiService
                .getPostsByThread(boardName, threadId, numPost);
    }
}
