package com.caramelheaven.lennach.utils.bus;

import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 14:35, 10/02/2019.
 */
public class RxBus {
    private static volatile RxBus instance;
    private PublishSubject<String> commonChannel = PublishSubject.create();
    private PublishSubject<String> chooseBoard = PublishSubject.create();

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    public void passChooseBoard(String boardName) {
        chooseBoard.onNext(boardName);
    }

    public void passCommonChannel(String value) {
        commonChannel.onNext(value);
    }

    public PublishSubject<String> getCommonChannel() {
        return commonChannel;
    }

    public PublishSubject<String> getChooseBoard() {
        return chooseBoard;
    }
}
