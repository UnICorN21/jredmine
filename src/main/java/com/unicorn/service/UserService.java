package com.unicorn.service;

import com.unicorn.domain.User;

/**
 * Created by Huxley on 6/29/15.
 */
public interface UserService {
    User userLogin(String username, String password);
}
