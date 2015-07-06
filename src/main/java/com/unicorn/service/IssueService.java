package com.unicorn.service;

import com.unicorn.bean.FormIssue;
import com.unicorn.bean.SimpleIssue;
import com.unicorn.domain.Issue;
import com.unicorn.domain.LogTime;
import com.unicorn.domain.User;

import java.util.List;

/**
 * Created by Huxley on 6/30/15.
 */
public interface IssueService {
    List<SimpleIssue> getIssues(String projectId, List<String> constraints, int pageIdx, int itemPerPage);

    Issue getIssue(int issueId);

    /**
     * Update issue.
     * Update with the given issue and generate log/history.
     * @param formIssue: the issue bean with the updated info.
     * @return updated issue
     */
    int updateIssue(FormIssue formIssue, User author, String notes);

    /**
     * Create new issue
     * Create with the given `formIssue` and generate activity(?, TODO)
     * @param formIssue
     * @param author
     * @return
     */
    Issue createIssue(FormIssue formIssue, User author);

    void logTime(LogTime logTime);
}
