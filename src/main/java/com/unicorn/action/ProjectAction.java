package com.unicorn.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.unicorn.domain.Project;
import com.unicorn.domain.User;
import com.unicorn.service.IssueService;
import com.unicorn.service.ProjectService;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Huxley on 6/29/15.
 */
@Namespace("/project")
@Scope("prototype")
@ParentPackage("base")
public class ProjectAction extends BaseAction<Project> {

    private static final int MAX_NUM_OF_LATEST_PROJECTS = 8;

    @Resource
    private ProjectService projectService;

    @Resource
    private IssueService issueService;

    private Project project;

    private List<Project> projectList;

    private boolean clozed;

    public boolean isClozed() {
        return clozed;
    }

    public void setClozed(boolean clozed) {
        this.clozed = clozed;
    }

    @Override
    public Project getModel() {
        if (null == project) project = new Project();
        return project;
    }

    /*
     * Do nothing!
     * Only used for generating the context of the value stack.
     */
    @Actions({
            @Action(value = "stub", results = {
                    @Result(name = SUCCESS, location = "/project_overview.jsp")
            }),
            @Action(value = "overview", results = {
                    @Result(name = SUCCESS, location = "/project_overview.jsp")
            }),
            @Action(value = "activity", results = {
                    @Result(name = SUCCESS, location = "/project_activity.jsp")
            }),
            @Action(value = "issues", results = {
                    @Result(name = SUCCESS, location = "/project_issues.jsp")
            }),
            @Action(value = "new", results = {
                    @Result(name = SUCCESS, location = "/project_new.jsp")
            }),
            @Action(value = "gantt", results = {
                    @Result(name = SUCCESS, location = "/project_gantt.jsp")
            }),
            @Action(value = "calendar", results = {
                    @Result(name = SUCCESS, location = "/project_calendar.jsp")
            }),
            @Action(value = "news", results = {
                    @Result(name = SUCCESS, location = "/project_news.jsp")
            }),
            @Action(value = "documents", results = {
                    @Result(name = SUCCESS, location = "/project_documents.jsp")
            }),
            @Action(value = "wiki", results = {
                    @Result(name = SUCCESS, location = "/project_wiki.jsp")
            }),
            @Action(value = "files", results = {
                    @Result(name = SUCCESS, location = "/project_files.jsp")
            }),
            @Action(value = "settings", results = {
                    @Result(name = SUCCESS, location = "/project_settings.jsp")
            })
    })
    public String execute() {
        if (null != project.getId()) {
            project = projectService.getProject(project.getId());
            session.put("projectId", project.getId());
        }
        return SUCCESS;
    }

    @Action(value = "list", results = @Result(location = "/projects.jsp"))
    public String listProjects() {
        projectList = projectService.getProjects(isClozed());
        return SUCCESS;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public List<Project> getLatestProjects() {
        return projectService.getLatestProjects(MAX_NUM_OF_LATEST_PROJECTS);
    }
}
