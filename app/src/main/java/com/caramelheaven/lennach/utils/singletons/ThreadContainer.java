package com.caramelheaven.lennach.utils.singletons;

import com.caramelheaven.lennach.models.model.thread.Post;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by CaramelHeaven on 17:38, 15/12/2018.
 * This is container for filtering data, when user click on answered text or reply.
 */
public class ThreadContainer {
    private static ThreadContainer INSTANCE;
    private List<Post> postList;

    public static ThreadContainer getInstance() {
        if (INSTANCE == null) {
            synchronized (ThreadContainer.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ThreadContainer();
                }
            }
        }

        return INSTANCE;
    }

    public void initPostsFromThread(List<Post> posts) {
        postList = posts;
    }

    public void clearPosts() {
        postList = null;
    }

    public Single<List<Post>> filteringDataByReference(String reference) {
        return Single.just(postList)
                .flattenAsObservable((Function<List<Post>, Iterable<Post>>) posts -> posts)
                .filter(post -> String.valueOf(post.getModernComment()).contains(reference))
                .toList();
    }
}
