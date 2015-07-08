package com.unicorn.action;

import com.unicorn.domain.Tracker;
import com.unicorn.service.TrackerService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;

/**
 * Created by Huxley on 7/8/15.
 */
@Namespace("/tracker")
@Scope("prototype")
@ParentPackage("base")
public class TrackerAction extends BaseAction<Tracker> {
    private static final String REDIRECT = "redirect";
    private static final String SUBMIT_TYPE_CREATE = "Create";
    private static final String SUBMIT_TYPE_CREATE_AND_CONTINUE = "Create and continue";

    public static final String TRACKER_CREATE_SUCCESS_FLAG = "tracker_create_success_flag";

    @Resource
    private TrackerService trackerService;

    private Tracker tracker;

    private String submitType;

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    @Override
    public Tracker getModel() {
        if (null == tracker) tracker = new Tracker();
        return tracker;
    }

    @Action(value = "create", results = {
            @Result(name = REDIRECT, type = REDIRECT, location = "/admin/trackers.do"),
            @Result(name = SUCCESS, location = "/tracker_new.jsp")
    })
    public String create() {
        try {
            tracker = trackerService.create(tracker);
            session.put(TRACKER_CREATE_SUCCESS_FLAG, true);
        } catch (Exception e) {
            e.printStackTrace();
            session.put(TRACKER_CREATE_SUCCESS_FLAG, false);
        }
        if (SUBMIT_TYPE_CREATE.equals(submitType)) return REDIRECT;
        else return SUCCESS;
    }

    @Action(value = "delete", results = @Result(type = REDIRECT, location = "/admin/trackers.do"))
    public String delete() {
        trackerService.delete(tracker.getId());
        return SUCCESS;
    }
}
