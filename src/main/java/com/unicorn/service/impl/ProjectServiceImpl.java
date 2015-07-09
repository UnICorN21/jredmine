package com.unicorn.service.impl;

import com.unicorn.dao.ProjectDao;
import com.unicorn.dao.TrackerDao;
import com.unicorn.dao.UserDao;
import com.unicorn.domain.Project;
import com.unicorn.domain.Tracker;
import com.unicorn.domain.User;
import com.unicorn.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Huxley on 6/29/15.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectDao projectDao;

    @Resource
    private TrackerDao trackerDao;

    @Resource
    private UserDao userDao;

    public Project getProject(String id) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("id", id);
        List<Project> projects = projectDao.findByProperties(properties, null);
        if (null != projects) return projects.get(0);
        return null;
    }

    public List<Project> getLatestProjects(int maxResult) {
        List<String> additions = new ArrayList<String>();
        return projectDao.findLatestProjects(maxResult);
    }

    public List<Project> getProjects(boolean closed) {
        return projectDao.findRootProjects(closed);
    }

    public Project create(Project project) {
        projectDao.save(project);
        return project;
    }

    public Project create(Project project, int[] trackerIds, String[] userIds) {
        Set<Tracker> trackers = new HashSet<Tracker>();
        for (int i = 0; i < trackerIds.length; ++i) {
            Tracker tracker = trackerDao.get(Tracker.class, trackerIds[i]);
            trackers.add(tracker);
        }
        Set<User> developers = new HashSet<User>();
        for (int i = 0; i < userIds.length; ++i) {
            User user = userDao.get(User.class, userIds[i]);
            developers.add(user);
        }
        project.setTrackers(trackers);
        project.setDevelopers(developers);
        return create(project);
    }

    public void delete(String id) {
        projectDao.delete(Project.class, id);
    }
}
