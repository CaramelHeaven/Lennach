package com.caramelheaven.lennach.utils.callbacks;

import com.caramelheaven.lennach.utils.channel.SomeData;

public interface BottomBarHandler {
    void interactionBottomVisibility(SomeData data);

    void scrollBehavior(String data);
}