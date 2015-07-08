package com.unicorn.service;

import com.unicorn.domain.Tracker;

import java.util.List;

/**
 * Created by Huxley on 7/8/15.
 */
public interface TrackerService {
    List<Tracker> get();

    Tracker create(Tracker tracker);

    void delete(int id);
}
