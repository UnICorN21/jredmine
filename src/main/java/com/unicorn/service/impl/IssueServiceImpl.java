package com.unicorn.service.impl;

import com.unicorn.bean.SimpleIssue;
import com.unicorn.dao.IssueDao;
import com.unicorn.domain.Issue;
import com.unicorn.domain.Project;
import com.unicorn.service.IssueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Huxley on 6/30/15.
 */
@Service
public class IssueServiceImpl implements IssueService {

    @Resource
    private IssueDao issueDao;

    private List<Issue> cacheForIssue;
    private String lastProjectIdForIssue;

    // TODO: Add `contstraints` support
    public List<SimpleIssue> getIssues(String projectId, List<String> constraints, int pageIdx, int itemPerPage) {
        Map<String, Object> priorities = new HashMap<String, Object>();
        priorities.put("project.id", projectId);

        List<Issue> issues = issueDao.findByProperties(priorities, null);

        List<SimpleIssue> simpleIssues = new ArrayList<SimpleIssue>();
        for (Issue issue: issues) {
            simpleIssues.add(new SimpleIssue(issue));
        }
        return simpleIssues;
    }

    public Issue getIssue(int issueId) {
        Map<String, Object> priorities = new HashMap<String, Object>();
        priorities.put("id", issueId);
        List<Issue> issues = issueDao.findByProperties(priorities, null);
        if (null != issues) {
            Issue issue = issues.get(0);
            if (!issue.getProject().getId().equals(lastProjectIdForIssue)) {
                lastProjectIdForIssue = issue.getProject().getId();
                cacheForIssue = new ArrayList<Issue>(issue.getProject().getIssues());
            }
            for (int i = 0; i < cacheForIssue.size(); ++i) {
                if (issue.getId() == cacheForIssue.get(i).getId()) {
                    issue.setIdx(i + 1);
                    issue.setPrevIssue(i-1 >= 0 ? cacheForIssue.get(i-1).getId() : -1);
                    issue.setNextIssue(i+1 < cacheForIssue.size() ? cacheForIssue.get(i+1).getId() : -1);
                }
            }

            return issue;
        }
        return null;
    }
}
