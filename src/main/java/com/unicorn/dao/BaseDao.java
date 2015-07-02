package com.unicorn.dao;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Huxley on 6/29/15.
 */
@Transactional
public interface BaseDao<T> {

    Class getGenericType();

    SessionFactory getSessionFactory();

    T get(Class<T> entityClazz , Serializable id);

    Serializable save(T entity);

    void update(T entity);

    void delete(T entity);

    void delete(Class<T> entityClazz , Serializable id);

    List<T> findByExample(T instance);

    List<T> findByProperties(Map<String, Object> properties, List<String> additions);

}
