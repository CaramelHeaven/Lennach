package com.caramelheaven.lennach.di.thread;

import com.caramelheaven.lennach.di.thread.post_list.PostListComponent;
import com.caramelheaven.lennach.di.thread.post_list.PostListModule;

import dagger.Subcomponent;

@ThreadScope
@Subcomponent(modules = {ThreadModule.class})
public interface ThreadComponent {
    PostListComponent plusPostListComponent(PostListModule postListModule);
}
