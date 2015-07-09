package com.unicorn.dao.impl;

import com.unicorn.dao.IssueDao;
import com.unicorn.domain.Issue;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Huxley on 6/30/15.
 */
@Repository
@Transactional
public class IssueDaoImpl extends BaseDaoImpl<Issue> implements IssueDao {
    public List<Issue> findIssues(String projectId, Date startDate, Date endDate) {
        String hql = "from Issue where project.id = :pid";
        if (null != startDate) hql += " and startDate <= :sd";
        if (null != endDate) hql += " and dueDate >= :ed";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("pid", projectId);
        if (null != startDate) query.setParameter("sd", startDate);
        if (null != endDate) query.setParameter("ed", endDate);
        return query.list();
    }
}
