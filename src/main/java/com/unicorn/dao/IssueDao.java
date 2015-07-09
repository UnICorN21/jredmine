package com.unicorn.dao;

import com.unicorn.domain.Issue;

import java.util.Date;
import java.util.List;

/**
 * Created by Huxley on 6/30/15.
 */
public interface IssueDao extends BaseDao<Issue> {
    List<Issue> findIssues(String projectId, Date startDate, Date endDate);
}
