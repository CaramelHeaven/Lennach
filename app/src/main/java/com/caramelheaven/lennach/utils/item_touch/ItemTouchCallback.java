package com.caramelheaven.lennach.utils.item_touch;

import com.caramelheaven.lennach.datasource.database.entity.helpers.PostsHelper;

public interface ItemTouchCallback {
    void sendAnswer(PostsHelper post);
}
