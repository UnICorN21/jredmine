package com.unicorn.service.impl;

import com.unicorn.dao.TrackerDao;
import com.unicorn.domain.Tracker;
import com.unicorn.service.TrackerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Huxley on 7/8/15.
 */
@Service
public class TrackerServiceImpl implements TrackerService {

    @Resource
    private TrackerDao trackerDao;

    public List<Tracker> get() {
        return trackerDao.findAll();
    }

    public Tracker create(Tracker tracker) {
        trackerDao.save(tracker);
        return tracker;
    }

    public void delete(int id) {
        trackerDao.delete(Tracker.class, id);
    }
}
