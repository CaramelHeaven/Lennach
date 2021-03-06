package com.caramelheaven.lennach.data.repository.thread;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.domain.ThreadRepository;
import com.caramelheaven.lennach.models.mapper.thread.ThreadMapper;
import com.caramelheaven.lennach.models.model.thread.Post;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 00:44, 08/12/2018.
 */
public class ThreadRemoteRepository implements ThreadRepository {

    private LennachApiService apiService;
    private ThreadMapper threadMapper;

    public ThreadRemoteRepository(LennachApiService apiService, ThreadMapper threadMapper) {
        this.apiService = apiService;
        this.threadMapper = threadMapper;
    }

    @Override
    public Single<List<Post>> getThread(String boardName, String threadId, String numPost) {
        return apiService
                .getPostsByThread(boardName, threadId, numPost)
                .map(threadMapper::map);
    }
}
