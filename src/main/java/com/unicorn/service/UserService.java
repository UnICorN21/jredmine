package com.unicorn.service;

import com.unicorn.domain.User;

import java.util.List;

/**
 * Created by Huxley on 6/29/15.
 */
public interface UserService {
    User userLogin(String username, String password);

    int register(User user);

    List<User> getUsers();
}
