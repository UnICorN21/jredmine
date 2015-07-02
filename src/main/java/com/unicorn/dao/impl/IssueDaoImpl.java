package com.unicorn.dao.impl;

import com.unicorn.dao.IssueDao;
import com.unicorn.domain.Issue;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Huxley on 6/30/15.
 */
@Repository
@Transactional
public class IssueDaoImpl extends BaseDaoImpl<Issue> implements IssueDao {

}
