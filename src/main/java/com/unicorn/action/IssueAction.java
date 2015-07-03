package com.unicorn.action;

import com.unicorn.bean.EditedIssue;
import com.unicorn.bean.SimpleIssue;
import com.unicorn.domain.Issue;
import com.unicorn.service.IssueService;
import net.sf.json.JSONArray;
import org.apache.struts2.convention.annotation.*;
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
    private static final int ITEM_PER_PAGE = 20;

    @Resource
    private IssueService issueService;

    private String projectId;

    private Issue issue;

    private EditedIssue editedIssue;

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

    public EditedIssue getEditedIssue() {
        return editedIssue;
    }

    public void setEditedIssue(EditedIssue editedIssue) {
        this.editedIssue = editedIssue;
    }

    @Action(value = "edit_issue", results = @Result(location = "/single_issue.jsp"))
    public String editIssue() {

        issue = issueService.getIssue(issue.getId());

        System.out.println("give me a breakpoint");

        return SUCCESS;
    }
}
