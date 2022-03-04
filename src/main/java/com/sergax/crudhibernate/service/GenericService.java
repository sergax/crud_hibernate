package com.sergax.crudhibernate.service;

import java.util.List;

public interface GenericService<T, Id> {
    
    T getById(Long id);

    List<T> getAll();

    void deleteById(Long id);

    T create(T t);

    T update(T t);
}
