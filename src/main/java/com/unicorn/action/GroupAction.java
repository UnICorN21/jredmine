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
    private static final String REDIRECT = "redirect";
    private static final String SUBMIT_TYPE_CREATE = "Create";
    private static final String SUBMIT_TYPE_CREATE_AND_CONTINUE = "Create and continue";

    public static final String GROUP_CREATE_SUCCESS_FLAG = "group_create_success_flag";

    @Resource
    private GroupService groupService;

    private Group group;

    private String submitType;

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    @Override
    public Group getModel() {
        if (null == group) group = new Group();
        return group;
    }

    @Action(value = "new", results = {
            @Result(name = REDIRECT, type = REDIRECT, location = "/admin/groups.do"),
            @Result(name = SUCCESS, location = "/group_new.jsp")
    })
    public String create() {
        try {
            group = groupService.create(group);
        } catch (Exception e) {
            e.printStackTrace();
            session.put(GROUP_CREATE_SUCCESS_FLAG, false);
        }
        if (SUBMIT_TYPE_CREATE.equals(submitType)) return REDIRECT;
        else return SUCCESS;
    }

    @Action(value = "delete", results = @Result(type = "redirect", location = "/admin/groups.do"))
    public String delete() {
        groupService.delete(group.getId());

        return SUCCESS;
    }
}
