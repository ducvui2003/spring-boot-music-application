package com.spring.delivery.mapper;

@FunctionalInterface
public interface EntityMapper<T, R> {
    R map(T entity);
}
