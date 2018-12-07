package com.caramelheaven.lennach.utils.bus.models;

/**
 * Created by CaramelHeaven on 22:34, 07/12/2018.
 * Pass data from BoardPresenter to MainActivity, set page to 1 and send data to thread
 */
public class HandlerViewPagerData {
    private ActionThread actionThread;

    public HandlerViewPagerData(ActionThread actionThread) {
        this.actionThread = actionThread;
    }

    public ActionThread getActionThread() {
        return actionThread;
    }
}
