package com.caramelheaven.lennach.domain.thread_use_case;

import com.caramelheaven.lennach.domain.ThreadRepository;
import com.caramelheaven.lennach.domain.base.BaseUseCase;
import com.caramelheaven.lennach.models.model.thread.Post;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 00:37, 08/12/2018.
 */
public class GetThread extends BaseUseCase<List<Post>> {
    private final ThreadRepository threadRepository;

    private String boardName, threadNum, numPost;

    public GetThread(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    @Override
    public Single<List<Post>> subscribeToData() {
        return threadRepository.getThread(boardName, threadNum, numPost);
    }

    public void setBaseData(String boardName, String threadNum, String numPost) {
        this.boardName = boardName;
        this.threadNum = threadNum;
        this.numPost = numPost;
    }
}
