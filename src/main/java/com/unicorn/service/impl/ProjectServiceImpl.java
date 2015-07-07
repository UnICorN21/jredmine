package com.unicorn.service.impl;

import com.unicorn.dao.ProjectDao;
import com.unicorn.domain.Project;
import com.unicorn.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Huxley on 6/29/15.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectDao projectDao;

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

    public void delete(String id) {
        projectDao.delete(Project.class, id);
    }
}
