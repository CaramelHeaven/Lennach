package com.caramelheaven.lennach.di.thread.post_list;

import com.caramelheaven.lennach.di.ActivityScope;
import com.caramelheaven.lennach.domain.ThreadRepository;
import com.caramelheaven.lennach.domain.thread_use_cases.GetThread;

import dagger.Module;
import dagger.Provides;

@Module
public class PostListModule {
    @ActivityScope
    @Provides
    GetThread provideGetThread(ThreadRepository threadRepository) {
        return new GetThread(threadRepository);
    }
}
