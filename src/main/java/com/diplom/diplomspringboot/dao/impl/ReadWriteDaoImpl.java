package com.diplom.diplomspringboot.dao.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class ReadWriteDaoImpl<K, T> {

    @PersistenceContext
    protected EntityManager entityManager;
    protected String className;
    protected final Class<T> persistentClass;
    protected String genericClassName;
    protected final String getAllQuery;

    public ReadWriteDaoImpl() {
        persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        genericClassName = persistentClass.toGenericString();
        className = genericClassName.substring(genericClassName.lastIndexOf('.') + 1);;
        getAllQuery = "FROM " + className;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public T update(T entity) {
        T mergedEntity;
        mergedEntity = entityManager.merge(entity);
        return mergedEntity;
    }

    public T getByKey(K key) {
        return entityManager.find(persistentClass, key);
    }

    public List<T> getAll() {
        return entityManager.createQuery(getAllQuery, persistentClass).getResultList();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteWithCascadeIgnore(K pk) {
        entityManager.flush();
        entityManager.clear();
        entityManager.createQuery("DELETE " + className + " WHERE  id=:Id")
                .setParameter("Id", pk)
                .executeUpdate();

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteWithCascadeEnableById(K pk) {
        T proxyObject = entityManager.getReference(persistentClass, pk);
        entityManager.remove(proxyObject);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteWithCascadeEnable(T entity) {
        entityManager.remove(entity);
    }
}
