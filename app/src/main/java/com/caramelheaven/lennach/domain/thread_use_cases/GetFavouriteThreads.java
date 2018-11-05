package com.caramelheaven.lennach.domain.thread_use_cases;

import com.caramelheaven.lennach.data.repository.thread.ThreadLocalRepository;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;

import java.util.List;

import io.reactivex.Single;

public class GetFavouriteThreads {
    private final ThreadLocalRepository threadLocalRepository;

    public GetFavouriteThreads(ThreadLocalRepository threadLocalRepository) {
        this.threadLocalRepository = threadLocalRepository;
    }

    public Single<List<Usenet>> provideFavouriteThreads(boolean favourite) {
        return threadLocalRepository.getFavouriteThreads(favourite);
    }
}
