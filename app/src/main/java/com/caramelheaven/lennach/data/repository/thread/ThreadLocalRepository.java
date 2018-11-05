package com.caramelheaven.lennach.data.repository.thread;

import com.caramelheaven.lennach.data.datasource.database.ThreadDao;
import com.caramelheaven.lennach.models.database.UsenetEntity;
import com.caramelheaven.lennach.models.mapper.thread.ThreadMapper;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class ThreadLocalRepository {
    private final ThreadMapper threadMapper;
    private final ThreadDao threadDao;

    public ThreadLocalRepository(ThreadMapper threadMapper, ThreadDao threadDao) {
        this.threadMapper = threadMapper;
        this.threadDao = threadDao;
    }

    public Single<List<Usenet>> getFavouriteThreads(boolean favourite) {
        return threadDao.getFavouriteThreads(favourite ? 1 : 0).map(threadMapper::map);
    }

    private void getHistoryLastEnteredThreads() {

    }
}
