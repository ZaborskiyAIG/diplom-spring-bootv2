package com.diplom.diplomspringboot.service.abstracts;

import java.util.List;

public interface ReadWriteService<K,T> {

    T update(T entity);

    T getByKey(K key);

    List<T> getAll();

    void deleteWithCascadeIgnore(K pk);

    void persist(T entity);

    void deleteWithCascadeEnableId(K id);

    void deleteWithCascadeEnable(T entity);

}
