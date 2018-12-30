package com.caramelheaven.lennach.utils.singletons;

import com.caramelheaven.lennach.models.model.thread.Post;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by CaramelHeaven on 17:38, 15/12/2018.
 * This is class for filtering data, when user click on answered text or reply.
 */
public class ThreadFilterPosts {
    private static ThreadFilterPosts INSTANCE;
    private List<Post> postList;

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

    public void initPostsFromThread(List<Post> posts) {
        postList = posts;
    }

    public void clearPosts() {
        postList = null;
    }

    public Single<List<Post>> filteringDataByReference(String reference) {
        String ref = reference.replaceAll("[^0-9]", "");

        return Single.just(postList)
                .flattenAsObservable((Function<List<Post>, Iterable<Post>>) posts -> posts)
                .filter(post -> post.getNum().equals(ref))
                .toList();
    }
}
