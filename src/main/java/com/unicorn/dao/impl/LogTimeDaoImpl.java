package com.unicorn.dao.impl;

import com.unicorn.dao.LogTimeDao;
import com.unicorn.domain.LogTime;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Huxley on 7/6/15.
 */
@Repository
@Transactional
public class LogTimeDaoImpl extends BaseDaoImpl<LogTime> implements LogTimeDao {
}
