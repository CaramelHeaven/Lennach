package com.caramelheaven.lennach.utils.singletons;

import com.caramelheaven.lennach.models.model.thread.Post;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by CaramelHeaven on 17:38, 15/12/2018.
 * This is class for filtering data, when user click on answered text or reply.
 */
public class ThreadFilterPosts<T extends Post> {
    private static ThreadFilterPosts INSTANCE;
    private List<T> postList;

    public static ThreadFilterPosts getInstance() {
        if (INSTANCE == null) {
            synchronized (ThreadFilterPosts.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ThreadFilterPosts();
                }
            }
        }

        return INSTANCE;
    }

    public void initPostsFromThread(List<T> posts) {
        postList = posts;
    }

    public void clearPosts() {
        postList = null;
    }

    public Single<List<T>> filterDateByTextLink(String reference) {
        String ref = reference.replaceAll("[^0-9]", "");

        return Single.just(postList)
                .flattenAsObservable((Function<List<T>, Iterable<T>>) posts -> posts)
                .filter(post -> post.getNum().equals(ref))
                .toList();
    }

    public Single<List<T>> filterDataByBtnReply(List<String> references) {
        return Single.just(postList)
                .flattenAsObservable((Function<List<T>, Iterable<T>>) ts -> ts)
                .filter(post -> references.contains(post.getNum()))
                .toList();
    }
}
