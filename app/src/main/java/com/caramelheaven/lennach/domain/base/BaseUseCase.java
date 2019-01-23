package com.caramelheaven.lennach.domain.base;

import io.reactivex.Completable;

/**
 * Created by CaramelHeaven on 00:38, 08/12/2018.
 */
public abstract class BaseUseCase<T> {
    public abstract T subscribeToData();
}
