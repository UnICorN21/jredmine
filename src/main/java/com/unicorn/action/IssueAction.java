package com.unicorn.action;

import com.unicorn.Utils;
import com.unicorn.bean.FormIssue;
import com.unicorn.bean.SimpleIssue;
import com.unicorn.domain.Issue;
import com.unicorn.domain.LogTime;
import com.unicorn.domain.User;
import com.unicorn.service.IssueService;
import net.sf.json.JSONArray;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Huxley on 6/30/15.
 */
@Namespace("/")
@Scope("prototype")
@ParentPackage("base")
public class IssueAction extends BaseAction<Issue> {
    private static final String REDIRECT = "REDIRECT";
    private static final String SUBMIT_TYPE_CREATE = "Create";
    private static final String SUBMIT_TYPE_CREATE_AND_CONTINUE = "Create and continue";

    public static final String ISSUE_CREATE_SUCCESS_FLAG = "issue_create_success_flag";
    public static final String ISSUE_UPDATE_SUCCESS_FLAG = "issue_update_success_flag";

    private static final int ITEM_PER_PAGE = 20;

    @Resource
    private IssueService issueService;

    private String projectId;

    private Issue issue;

    private FormIssue formIssue;
    private LogTime loggedTime;
    private String notes;

    private String submitType;

    private int pageIdx;

    @Override
    public Issue getModel() {
        if (null == issue) issue = new Issue();
        return issue;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getPageIdx() {
        return pageIdx;
    }

    public void setPageIdx(int pageIdx) {
        this.pageIdx = pageIdx;
    }

    @Action(value = "ajax_issues", results = @Result(type = "json"))
    public String ajaxIssues() throws Exception {
        List<SimpleIssue> simpleIssues = issueService.getIssues(projectId, null, 0, ITEM_PER_PAGE);

        JSONArray json = JSONArray.fromObject(simpleIssues);
        response.setContentType("text/json; charset=utf-8");
        response.getWriter().print(json);

        return null;
    }

    @Action(value = "single_issue", results = @Result(location = "/single_issue.jsp"))
    public String singleIssue() {
        issue = issueService.getIssue(issue.getId());
        return SUCCESS;
    }

    public FormIssue getFormIssue() {
        return formIssue;
    }

    public void setFormIssue(FormIssue formIssue) {
        this.formIssue = formIssue;
    }

    public LogTime getLoggedTime() {
        return loggedTime;
    }

    public void setLoggedTime(LogTime loggedTime) {
        this.loggedTime = loggedTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = "".equals(notes) ? null : notes;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    @Action(value = "edit_issue", results = @Result(type = "redirect", location = "single_issue.do?id=${formIssue.id}"))
    public String editIssue() {
        User author = Utils.getCurrentUser();
        if (null != loggedTime.getSpentTime() && 0 != loggedTime.getSpentTime()) {
            loggedTime.setUser(author);
            issue.setId(formIssue.getId());
            loggedTime.setIssue(issue);
            issueService.logTime(loggedTime);
            session.put(ISSUE_UPDATE_SUCCESS_FLAG, true);
        }

        if (1 == issueService.updateIssue(formIssue, author, notes))
            session.put(ISSUE_UPDATE_SUCCESS_FLAG, true);

        return SUCCESS;
    }

    @Action(value = "new_issue", results = {
            @Result(name = REDIRECT, type = "redirect", location = "single_issue.do?id=${id}"),
            @Result(name = SUCCESS, location = "/project_issue_new.jsp")
    })
    public String newIssue() {
        User author = Utils.getCurrentUser();
        try {
            issue = issueService.create(formIssue, author);
            session.put(ISSUE_CREATE_SUCCESS_FLAG, true);
        } catch (Exception e) {
            e.printStackTrace();
            session.put(ISSUE_CREATE_SUCCESS_FLAG, false);
        }

        if (SUBMIT_TYPE_CREATE.equals(submitType)) return REDIRECT;
        else return SUCCESS;
    }
}
