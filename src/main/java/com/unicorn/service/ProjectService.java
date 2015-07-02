package com.unicorn.service;

import com.unicorn.domain.Project;
import com.unicorn.domain.User;

import java.util.List;

/**
 * Created by Huxley on 6/29/15.
 */
public interface ProjectService {
    List<Project> getLatestProjects(int maxResult);

    Project getProject(String id);

    List<Project> getProjects(boolean closed);
}
