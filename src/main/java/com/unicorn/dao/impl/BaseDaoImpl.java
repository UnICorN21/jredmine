package com.unicorn.dao.impl;

import com.unicorn.dao.BaseDao;
import javafx.util.Pair;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by Huxley on 6/29/15.
 */
@Repository
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {

    @Resource
    private SessionFactory sessionFactory;

    public Class getGenericType() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        return (Class)params[0];
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void delete(T entity) {
        getSessionFactory().getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public T get(Class<T> entityClazz, Serializable id) {
        return (T) getSessionFactory().getCurrentSession().get(entityClazz, id);
    }

    public Serializable save(T entity) {
        Serializable result = getSessionFactory().getCurrentSession().save(entity);
        getSessionFactory().getCurrentSession().flush();
//        getSessionFactory().getCurrentSession().clear();
        return result;
    }

    public void update(T entity) {
        getSessionFactory().getCurrentSession().saveOrUpdate(entity);
    }

    public int update(Object id, List<Pair<String, Object>> properties) {
        if (null == properties || 0 == properties.size()) return 0;
        String hql = "update " + getGenericType().getSimpleName() + " set";
        boolean flag = false;

        for (int i = 0; i < properties.size(); ++i) {
            Pair<String, Object> pair = properties.get(i);
            if (flag) hql += ", ";
            else { flag = !flag; hql += " "; }
            hql += pair.getKey() + " = :p" + String.valueOf(i);
        }
        hql += " where id = :id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);

        for (int i = 0; i < properties.size(); ++i) {
            query.setParameter("p" + String.valueOf(i), properties.get(i).getValue());
        }

        query.setParameter("id", id);
        int result = query.executeUpdate();
        getSessionFactory().getCurrentSession().clear();
        return result;
    }

    public void delete(Class<T> entityClazz, Serializable id) {
        getSessionFactory().getCurrentSession()
                .createQuery("delete " + entityClazz.getSimpleName()
                        + " en where en.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Session session = getSessionFactory().getCurrentSession();
        String hql = "from " + getGenericType().getSimpleName();
        Query query = session.createQuery(hql);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(T instance) {
        Session session = getSessionFactory().getCurrentSession();
        return session.createCriteria(instance.getClass()).add(Example.create(instance)).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByProperties(Map<String, Object> properties, List<String> additions) {
        String hql = "from " + getGenericType().getSimpleName() + " where 1 = 1";

        if (null != properties) {
            for (Map.Entry<String, Object> entity: properties.entrySet()) {
                hql += String.format(" and %s = ? ", entity.getKey(), entity.getKey());
            }
        }

        if (null != additions) {
            for (int i = 0; i < additions.size(); ++i) {
                hql += " " + additions.get(i);
            }
        }
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);

        int idx = 0;

        if (null != properties) {
            for (Map.Entry<String, Object> entity: properties.entrySet()) {
                query.setParameter(idx++, entity.getValue());
            }
        }

        return query.list();
    }
}
