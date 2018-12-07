package com.caramelheaven.lennach.di.thread;

import com.caramelheaven.lennach.data.datasource.network.LennachApiService;
import com.caramelheaven.lennach.data.repository.thread.ThreadRemoteRepository;
import com.caramelheaven.lennach.domain.ThreadRepository;
import com.caramelheaven.lennach.domain.thread_use_case.GetThread;
import com.caramelheaven.lennach.models.mapper.thread.ThreadMapper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaramelHeaven on 22:27, 07/12/2018.
 */
@Module
public class ThreadModule {

    @ThreadScope
    @Provides
    GetThread provideGetThread(ThreadRepository threadRepository) {
        return new GetThread(threadRepository);
    }

    @ThreadScope
    @Provides
    ThreadRepository provideThreadRepository(LennachApiService apiService) {
        return new ThreadRemoteRepository(apiService);
    }
}
