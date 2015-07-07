package com.unicorn.dao.impl;

import com.unicorn.dao.GroupDao;
import com.unicorn.domain.Group;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Huxley on 7/7/15.
 */
@Repository
@Transactional
public class GroupDaoImpl extends BaseDaoImpl<Group> implements GroupDao {

}
