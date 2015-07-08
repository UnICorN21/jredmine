package com.unicorn.action;

import com.unicorn.domain.Group;
import com.unicorn.domain.Project;
import com.unicorn.domain.Tracker;
import com.unicorn.domain.User;
import com.unicorn.service.GroupService;
import com.unicorn.service.ProjectService;
import com.unicorn.service.TrackerService;
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

    @Resource
    private GroupService groupService;

    @Resource
    private TrackerService trackerService;

    private List<User> users;

    private List<Project> projects;

    private List<Group> groups;

    private List<Tracker> trackers;

    public List<User> getUsers() {
        return users;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Tracker> getTrackers() {
        return trackers;
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

    @Action(value = "groups", results = @Result(location = "/admin_groups.jsp"))
    public String groups() {
        groups = groupService.get();
        return SUCCESS;
    }

    @Action(value = "trackers", results = @Result(location = "/admin_trackers.jsp"))
    public String trackers() {
        trackers = trackerService.get();
        return SUCCESS;
    }
}
