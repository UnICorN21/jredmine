package com.unicorn.service.impl;

import com.unicorn.dao.UserDao;
import com.unicorn.domain.GrantedAuthority;
import com.unicorn.domain.User;
import com.unicorn.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Huxley on 6/29/15.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final String ROLE_USER = "ROLE_USER";

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Resource
    private UserDao userDao;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Map<String, Object> properites = new HashMap<String, Object>();
        properites.put(USERNAME, s);
        try {
            return userDao.findByProperties(properites, null).get(0);
        } catch (Exception e) {
            throw new UsernameNotFoundException(s + " not found");
        }
    }

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
            // Set default authority.
            GrantedAuthority grantedAuthority = new GrantedAuthority();
            grantedAuthority.setAuthority(ROLE_USER);
            grantedAuthority.setUser(user);
            Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
            grantedAuthorities.add(grantedAuthority);
            user.setGrantedAuthorities(grantedAuthorities);
            // Save new user.
            userDao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public List<User> getUsers() {
        return userDao.findAll();
    }
}
