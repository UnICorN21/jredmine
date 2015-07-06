package com.unicorn.action;

import com.unicorn.domain.Project;
import com.unicorn.domain.User;
import com.unicorn.service.ProjectService;
import com.unicorn.service.UserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Huxley on 7/6/15.
 */
@Controller
@Namespace("/admin")
@Scope("prototype")
@ParentPackage("base")
public class AdminAction extends BaseAction {

    @Resource
    private ProjectService projectService;

    @Resource
    private UserService userService;

    private List<User> users;

    private List<Project> projects;

    public List<User> getUsers() {
        return users;
    }

    public List<Project> getProjects() {
        return projects;
    }

    @Action(value = "projects", results = @Result(location = "/admin_projects.jsp"))
    public String projects() {
        projects = projectService.getProjects(true);
        return SUCCESS;
    }

    @Action(value = "users", results = @Result(location = "/admin_users.jsp"))
    public String users() {
        users = userService.getUsers();
        return SUCCESS;
    }
}
