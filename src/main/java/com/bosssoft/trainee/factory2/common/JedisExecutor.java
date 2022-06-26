package com.bosssoft.trainee.factory2.common;

@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws Exception;
}
