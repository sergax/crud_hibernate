package com.sergax.crudhibernate.repository;

import java.util.List;

public interface GenericRepository<T, Id> {
    T getById(Long id);

    List<T> getAll();

    void deleteById(T t);

    T create(T t);

    T update(T t);
}
