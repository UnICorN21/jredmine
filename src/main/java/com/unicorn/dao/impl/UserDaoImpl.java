package com.unicorn.dao.impl;

import com.unicorn.dao.UserDao;
import com.unicorn.domain.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Huxley on 6/29/15.
 */
@Repository
@Transactional
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

}
