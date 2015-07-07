package com.unicorn.action;

import com.unicorn.domain.Group;
import com.unicorn.service.GroupService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Huxley on 7/7/15.
 */
@Controller
@Namespace("/group")
@Scope("prototype")
@ParentPackage("base")
public class GroupAction extends BaseAction<Group> {

    @Resource
    private GroupService groupService;

    private Group group;

    @Override
    public Group getModel() {
        if (null == group) group = new Group();
        return group;
    }

    @Action(value = "delete", results = @Result(type = "redirect", location = "/admin/groups.do"))
    public String delete() {
        groupService.delete(group.getId());

        return SUCCESS;
    }
}
