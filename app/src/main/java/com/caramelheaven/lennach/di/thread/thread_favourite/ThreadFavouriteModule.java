package com.caramelheaven.lennach.di.thread.thread_favourite;

import android.content.Context;

import com.caramelheaven.lennach.Lennach;
import com.caramelheaven.lennach.data.datasource.database.ThreadDao;
import com.caramelheaven.lennach.data.repository.thread.ThreadLocalRepository;
import com.caramelheaven.lennach.di.ActivityScope;
import com.caramelheaven.lennach.domain.thread_use_cases.GetFavouriteThreads;
import com.caramelheaven.lennach.models.mapper.thread.ThreadMapper;

import dagger.Module;
import dagger.Provides;

@Module
public class ThreadFavouriteModule {
    @ActivityScope
    @Provides
    GetFavouriteThreads provideGetFavouriteThreads(ThreadLocalRepository threadLocalRepository) {
        return new GetFavouriteThreads(threadLocalRepository);
    }

    @ActivityScope
    @Provides
    ThreadLocalRepository provideThreadLocalRepository(ThreadMapper mapper, ThreadDao threadDao) {
        return new ThreadLocalRepository(mapper, threadDao);
    }

    @ActivityScope
    @Provides
    ThreadDao provideThreadDao(Context context) {
        Lennach lennach = (Lennach) context;
        return lennach.getDatabase().getUsenetDao();
    }
}
