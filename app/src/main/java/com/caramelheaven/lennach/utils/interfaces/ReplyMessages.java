package com.caramelheaven.lennach.utils.interfaces;

import android.content.Context;
import android.view.View;

/**
 * Created by CaramelHeaven on 19:52, 08/01/2019.
 */
public interface ReplyMessages {
    void expandFromNullToReply(View view, Context context);

    void expandFromReplyToAll(View view, int screenHeight, Context context);

    void collapseFromAllToReply(View view, Context context);

    void collapseFromReplyToNull(View view, Context context);
}
