package com.caramelheaven.lennach.di.thread;

import com.caramelheaven.lennach.di.thread.post_list.PostListComponent;
import com.caramelheaven.lennach.di.thread.post_list.PostListModule;
import com.caramelheaven.lennach.di.thread.thread_favourite.ThreadFavouriteComponent;
import com.caramelheaven.lennach.di.thread.thread_favourite.ThreadFavouriteModule;

import dagger.Subcomponent;

@ThreadScope
@Subcomponent(modules = {ThreadModule.class})
public interface ThreadComponent {
    PostListComponent plusPostListComponent(PostListModule postListModule);

    ThreadFavouriteComponent plusThreadFavouriteComponent(ThreadFavouriteModule threadFavouriteModule);
}
