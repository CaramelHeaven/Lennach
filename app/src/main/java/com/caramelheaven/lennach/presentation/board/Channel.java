package com.caramelheaven.lennach.presentation.board;

import io.reactivex.subjects.PublishSubject;

public class Channel {

    private static Channel INSTANCE;
    private static PublishSubject<Boolean> publishSubject;

    public static Channel getInstance() {
        if (INSTANCE == null) {
            synchronized (Channel.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Channel();
                    publishSubject = PublishSubject.create();
                }
            }
        }
        return INSTANCE;
    }

    public static PublishSubject<Boolean> getPublishSubject() {
        return publishSubject;
    }

    public static void sendData(boolean flag) {
        publishSubject.onNext(flag);
    }
}
