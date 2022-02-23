package com.sergax.crudhibernate.repository;

import java.util.List;

public interface GenericRepository<T, Id> {
    T getById(Long id);

    List<T> getAll();

    void deleteById(Long id);

    T create(T t);

    T update(T t);
}
