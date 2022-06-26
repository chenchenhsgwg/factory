package com.bosssoft.trainee.factory2.common;

@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}

