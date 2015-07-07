package com.unicorn.service;

import com.unicorn.domain.Group;

import java.util.List;

/**
 * Created by Huxley on 7/7/15.
 */
public interface GroupService {
    List<Group> get();

    void delete(int id);
}
