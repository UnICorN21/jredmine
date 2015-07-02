package com.unicorn.dao.impl;

import com.unicorn.dao.ProjectDao;
import com.unicorn.domain.Project;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Huxley on 6/29/15.
 */
@Repository
@Transactional
public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao {

    @SuppressWarnings("unchecked")
    public List<Project> findLatestProjects(int maxResult) {
        String hql = "from Project where closed = false order by createTime desc";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setMaxResults(maxResult);
        return query.list();
    }

    public List<Project> findRootProjects(boolean closed) {

        String hql = "from Project where parent is null";
        if (!closed) hql += " and closed = ?";
        hql += " order by createTime desc";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        if (!closed) query.setParameter(0, false);
        return query.list();
    }
}
