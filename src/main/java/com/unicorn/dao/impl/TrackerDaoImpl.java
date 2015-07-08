package com.unicorn.dao.impl;

import com.unicorn.dao.TrackerDao;
import com.unicorn.domain.Tracker;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Huxley on 7/8/15.
 */
@Repository
@Transactional
public class TrackerDaoImpl extends BaseDaoImpl<Tracker> implements TrackerDao {
}
