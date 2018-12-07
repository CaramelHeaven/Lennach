package com.caramelheaven.lennach.domain.base;

import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 00:38, 08/12/2018.
 */
public abstract class BaseUseCase<T> {
    public abstract Single<T> subscribeToData();
}
