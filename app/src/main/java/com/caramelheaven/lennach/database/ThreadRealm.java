package com.caramelheaven.lennach.database;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ThreadRealm extends RealmObject {
    private RealmList<PostDB> posts;

    public RealmList<PostDB> getPosts() {
        return posts;
    }

    public void setPosts(RealmList<PostDB> posts) {
        this.posts = posts;
    }

    public ThreadRealm toEntityThread() {
        ThreadRealm thread = new ThreadRealm();
        thread.posts = posts;
        return thread;
    }

    public void setFromEntityThread(ThreadRealm thread) {
        posts = thread.posts;
    }
}
