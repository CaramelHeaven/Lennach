package com.caramelheaven.lennach.models.mapper

/**
 * Created by CaramelHeaven on 16:26, 03/02/2019.
 *
 * @param T  - output class
 * @param T1 - enter class
 */
abstract class Mapper<T, T1> {
    abstract fun map(enterData: T1): T

    protected abstract fun fillData(outputData: T, enterData: T1)
}
