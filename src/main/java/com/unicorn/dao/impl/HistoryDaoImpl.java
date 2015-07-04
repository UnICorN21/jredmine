package com.unicorn.dao.impl;

import com.unicorn.dao.HistoryDao;
import com.unicorn.domain.History;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Huxley on 7/3/15.
 */
@Repository
@Transactional
public class HistoryDaoImpl extends BaseDaoImpl<History> implements HistoryDao {

}
