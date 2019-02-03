package com.caramelheaven.lennach.domain.base

/**
 * Created by CaramelHeaven on 16:37, 03/02/2019.
 */
abstract class BaseUseCase<T> {
    abstract fun handler(): T
}