package com.unicorn.service.impl;

import com.unicorn.dao.UserDao;
import com.unicorn.domain.User;
import com.unicorn.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Huxley on 6/29/15.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Resource
    private UserDao userDao;

    public User userLogin(String username, String password) {
        if (null == username || null == password) return null;
        Map<String, Object> properites = new HashMap<String, Object>();
        properites.put(USERNAME, username);
        properites.put(PASSWORD, password);

        List<User> users = userDao.findByProperties(properites, null);
        if (0 != users.size()) return users.get(0);
        else return null;
    }

    public int register(User user) {
        try {
            userDao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
