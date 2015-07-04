package com.unicorn.service;

import com.unicorn.bean.EditedIssue;
import com.unicorn.bean.SimpleIssue;
import com.unicorn.domain.Issue;
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
     * Update with the given issue and generate log/history(TODO).
     * @param editedIssue: the issue bean with the updated info.
     * @return updated issue
     */
    int updateIssue(EditedIssue editedIssue, User author);
}
