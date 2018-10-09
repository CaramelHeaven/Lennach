package com.caramelheaven.lennach.ui.thread;

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SendMessageListener {

    @StateStrategyType(value = SingleStateStrategy.class)
    void updateThread();
}
