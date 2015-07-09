package com.unicorn.service;

import com.unicorn.domain.Project;

import java.util.List;

/**
 * Created by Huxley on 6/29/15.
 */
public interface ProjectService {
    List<Project> getLatestProjects(int maxResult);

    Project getProject(String id);

    List<Project> getProjects(boolean closed);

    Project create (Project project);

    Project create (Project project, int[] trackerIds, String[] userIds);

    Project update(Project project, String managerId, String[] developerIds, int[] trackerIds, String parentId);

    void delete(String id);
}
