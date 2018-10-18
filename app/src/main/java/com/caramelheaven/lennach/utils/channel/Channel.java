package com.caramelheaven.lennach.utils.channel;

import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

public class Channel {

    private static Channel INSTANCE;
    private static PublishSubject<SomeData> publishSubject;

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

    public static PublishSubject<SomeData> getPublishSubject() {
        return publishSubject;
    }

    public static void sendData(SomeData data) {
        Timber.d("sedning data: " + data.getData());
        publishSubject.onNext(data);
    }
}
