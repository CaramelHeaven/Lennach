package com.caramelheaven.lennach.datasource.repository;

import com.caramelheaven.lennach.datasource.database.LennachDatabase;
import com.caramelheaven.lennach.datasource.database.entity.helpers.PostsHelper;
import com.caramelheaven.lennach.datasource.database.entity.iFile;
import com.caramelheaven.lennach.datasource.database.entity.iPost;
import com.caramelheaven.lennach.datasource.model.File;
import com.caramelheaven.lennach.datasource.model.Post;
import com.caramelheaven.lennach.datasource.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ThreadRepository {

    private final ApiService apiService;
    private final LennachDatabase database;
    private static String THREAD_NUMBER = "";
    private CompositeDisposable disposable;

    @Inject
    public ThreadRepository(ApiService apiService, LennachDatabase database) {
        this.apiService = apiService;
        this.database = database;
        disposable = new CompositeDisposable();
    }

    public void getPosts(String boardName, String threadNum, String num) {
        disposable.add(apiService.getPostsByThreadId(boardName, threadNum, num)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(posts -> {
                    String threadId = posts.get(0).getNum();
                    THREAD_NUMBER = threadId;
                    for (Post post : posts) {
                        iPost iPost = new iPost(post.getNum(), post.getBanned(), post.getComment(),
                                post.getTimestamp(), post.getOp(), post.getDate(), post.getSubject(), threadId);
                        List<iFile> iFiles = new ArrayList<>();
                        for (File file : post.getFiles()) {
                            iFile iFile = new iFile(file.getDisplayname(), file.getDisplayname(),
                                    file.getFullname(), file.getHeight(), file.getWidth(),
                                    file.getPath(), file.getSize(), file.getThumbnail(), post.getNum());
                            iFiles.add(iFile);
                        }
                        database.postDao().insertPost(iPost);
                        database.fileDao().insertFiles(iFiles);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

//    public Flowable<List<PostsHelper>> getPostsFromDatabase(String threadNumber) {
//        return database.postDao().getPostsFromThread(threadNumber);
//    }

    public void clearRequests() {
        disposable.clear();
    }
}
