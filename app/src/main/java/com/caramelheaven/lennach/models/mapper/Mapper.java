package com.caramelheaven.lennach.models.mapper;

/**
 * Created by CaramelHeaven on 20:54, 23/01/2019.
 */
public abstract class Mapper<T, T1> {
    protected abstract T map(T1 value);

    protected abstract void fillData(T myData, T1 values);
}
