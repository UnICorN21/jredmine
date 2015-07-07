package com.unicorn.service.impl;

import com.unicorn.dao.GroupDao;
import com.unicorn.domain.Group;
import com.unicorn.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Huxley on 7/7/15.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupDao groupDao;

    public List<Group> get() {
        return groupDao.findAll();
    }

    public void delete(int id) {
        groupDao.delete(Group.class, id);
    }
}
