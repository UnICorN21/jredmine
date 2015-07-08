package com.unicorn.action;

import com.unicorn.Utils;
import com.unicorn.domain.Project;
import com.unicorn.domain.User;
import com.unicorn.service.IssueService;
import com.unicorn.service.ProjectService;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Huxley on 6/29/15.
 */
@Namespace("/project")
@Scope("prototype")
@ParentPackage("base")
public class ProjectAction extends BaseAction<Project> {
    private static final String REDIRECT = "redirect";
    private static final String SUBMIT_TYPE_CREATE = "Create";
    private static final String SUBMIT_TYPE_CREATE_AND_CONTINUE = "Create and continue";

    public static final String PROJECT_CREATE_SUCCESS_FLAG = "project_create_success_flag";

    public static final String CURRENT_PROJECT = "currentProject";
    private static final int MAX_NUM_OF_LATEST_PROJECTS = 8;

    @Resource
    private ProjectService projectService;

    @Resource
    private IssueService issueService;

    private Project project;
    private String parentId;
    private boolean inherit;

    private String submitType;

    private List<Project> projectList;

    private boolean clozed;

    public boolean isClozed() {
        return clozed;
    }

    public void setClozed(boolean clozed) {
        this.clozed = clozed;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean isInherit() {
        return inherit;
    }

    public void setInherit(boolean inherit) {
        this.inherit = inherit;
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
                    @Result(name = SUCCESS, location = "/project_issue_new.jsp")
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
            session.put(CURRENT_PROJECT, project);
        }
        return SUCCESS;
    }

    @Action(value = "list", results = @Result(location = "/projects.jsp"))
    public String listProjects() {
        projectList = projectService.getProjects(isClozed());
        return SUCCESS;
    }

    @Action(value = "create", results = {
            @Result(name = REDIRECT, type = REDIRECT, location = "overview.do?id=${id}"),
            @Result(name = SUCCESS, location = "/project_new.jsp")
    })
    public String create() {
        if (0 != parentId.length()) {
            Project parent = projectService.getProject(parentId);
            project.setParent(parent);
            if (inherit) {
                Set<User> developers = new HashSet<User>(parent.getDevelopers());
                project.setDevelopers(developers);
            }
        }
        User manager = Utils.getCurrentUser();
        project.setUserByManager(manager);
        try {
            project = projectService.create(project);
            session.put(CURRENT_PROJECT, project);
            session.put(PROJECT_CREATE_SUCCESS_FLAG, true);
        } catch (Exception e) {
            e.printStackTrace();
            session.put(PROJECT_CREATE_SUCCESS_FLAG, false);
        }
        if (SUBMIT_TYPE_CREATE.equals(submitType)) return REDIRECT;
        else return SUCCESS;
    }

    @Action(value = "delete", results = @Result(type = REDIRECT, location = "/admin/projects.do"))
    public String delete() {
        try {
            projectService.delete(project.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    @Action(value = "copy", results = @Result(type = REDIRECT, location = "/admin/projects.do"))
    public String copy() {
        project = projectService.getProject(project.getId());
        try {
            Project copied = project.clone();
            project = projectService.create(copied);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public List<Project> getLatestProjects() {
        return projectService.getLatestProjects(MAX_NUM_OF_LATEST_PROJECTS);
    }
}
