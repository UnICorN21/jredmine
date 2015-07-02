package com.unicorn.service;

import com.unicorn.bean.SimpleIssue;
import com.unicorn.domain.Issue;

import java.util.List;

/**
 * Created by Huxley on 6/30/15.
 */
public interface IssueService {
    List<SimpleIssue> getIssues(String projectId, List<String> constraints, int pageIdx, int itemPerPage);

    Issue getIssue(int issueId);
}
