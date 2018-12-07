package com.caramelheaven.lennach.di.thread;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by CaramelHeaven on 22:27, 07/12/2018.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadScope {
}
