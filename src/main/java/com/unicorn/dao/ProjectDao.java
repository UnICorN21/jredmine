package com.unicorn.dao;

import com.unicorn.domain.Project;

import java.util.List;

/**
 * Created by Huxley on 6/29/15.
 */
public interface ProjectDao extends BaseDao<Project> {
    List<Project> findLatestProjects(int maxResult);

    List<Project> findRootProjects(boolean closed);
}
