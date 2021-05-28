package com.diplom.diplomspringboot.dao.abstracts;

import java.util.List;

public interface ReadWriteDao<K,T> {

    T update(T entity);

    T getByKey(K key);

    List<T> getAll();

    void deleteWithCascadeIgnore(K pk);

    void persist(T entity);

    void deleteWithCascadeEnableById(K id);

    void deleteWithCascadeEnable(T entity);

}
