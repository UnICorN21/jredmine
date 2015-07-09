package com.unicorn.service.impl;

import com.unicorn.Utils;
import com.unicorn.bean.FormIssue;
import com.unicorn.bean.SimpleIssue;
import com.unicorn.dao.HistoryDao;
import com.unicorn.dao.IssueDao;
import com.unicorn.dao.LogTimeDao;
import com.unicorn.domain.*;
import com.unicorn.service.IssueService;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Huxley on 6/30/15.
 */
@Service
public class IssueServiceImpl implements IssueService {

    @Resource
    private IssueDao issueDao;

    @Resource
    private HistoryDao historyDao;

    @Resource
    private LogTimeDao logTimeDao;

    private List<Issue> cacheForIssue;
    private String lastProjectIdForIssue;

    // TODO: Add `contstraints` support
    public List<SimpleIssue> getIssues(String projectId, Date startDate, Date endDate) {
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
        if (null != issues && 0 != issues.size()) {
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

    public int updateIssue(FormIssue formIssue, User author, String notes) {
        Issue target = getIssue(formIssue.getId());
        FormIssue origin = new FormIssue(target);
        if ("".equals(formIssue.getDescription())) formIssue.setDescription(target.getDescription());
        List<Pair<String, Pair<Object, Object>>> log = Utils.diff(origin, formIssue);

        List<Pair<String, Object>> priorities = new ArrayList<Pair<String, Object>>();

        Set<IssueLog> logs = new HashSet<IssueLog>();

        History history = new History(target, logs, author, notes);

        for (Pair<String, Pair<Object, Object>> item: log) {
            String property = item.getKey();
            String oldValue = null != item.getValue().getKey() ? Utils.humanize(item.getValue().getKey().toString() ) : null;
            String newValue = null != item.getValue().getValue() ? Utils.humanize(item.getValue().getValue().toString()) : null;
            logs.add(new IssueLog(history, Utils.humanize(property), oldValue, newValue));
            priorities.add(new Pair<String, Object>(Utils.normalizeSQLProperty(property), item.getValue().getValue()));
        }

        if (0 == priorities.size()) return 0; // Prevent a void update.

        historyDao.save(history);
        return issueDao.update(formIssue.getId(), priorities);
    }


    public Issue create(FormIssue formIssue, User author) {
        Issue issue = new Issue(formIssue, author);
        issueDao.save(issue);

        return issue;
    }

    public void logTime(LogTime logTime) {
        logTimeDao.save(logTime);
    }
}
