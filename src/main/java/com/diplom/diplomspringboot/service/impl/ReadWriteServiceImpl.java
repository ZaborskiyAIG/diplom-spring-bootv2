package com.diplom.diplomspringboot.service.impl;

import com.diplom.diplomspringboot.dao.abstracts.ReadWriteDao;
import com.diplom.diplomspringboot.service.abstracts.ReadWriteService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class ReadWriteServiceImpl<K,T> implements ReadWriteService<K,T> {

    private final ReadWriteDao<K, T> readWriteDao;

    public ReadWriteServiceImpl(ReadWriteDao<K, T> readWriteDao) {
        this.readWriteDao = readWriteDao;
    }

    @Transactional
    public T update(T e) {
        return readWriteDao.update(e);
    }

    @Transactional
    public void persist(T entity) {
        readWriteDao.persist(entity);
    }

    public T getByKey(K id) {
        return readWriteDao.getByKey(id);
    }

    public List<T> getAll() {
        return readWriteDao.getAll();
    }

    @Transactional
    public void deleteWithCascadeIgnore(K id) {
        readWriteDao.deleteWithCascadeIgnore(id);
    }

    @Transactional
    public void deleteWithCascadeEnableId(K id) {
        readWriteDao.deleteWithCascadeEnableById(id);
    }

    @Transactional
    public void deleteWithCascadeEnable(T entity) {
        readWriteDao.deleteWithCascadeEnable(entity);
    }

}
