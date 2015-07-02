package com.unicorn.bean;

import com.unicorn.domain.Issue;
import com.unicorn.domain.Issue.Priority;
import com.unicorn.domain.Issue.Status;
import com.unicorn.domain.Issue.Tracker;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Huxley on 7/1/15.
 */
public class SimpleIssue {
    private int id;
    private String assignee;
    private String subject;
    private Priority priority;
    private Status status;
    private Tracker tracker;
    private String updateTime;


    public SimpleIssue(Issue issue) {
        this.id = issue.getId();
        this.assignee = issue.getUserByAssignee().getUsername();
        this.subject = issue.getSubject();
        this.priority = issue.getPriority();
        this.status = issue.getStatus();
        this.tracker = issue.getTracker();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.updateTime = df.format(issue.getUpdateTime());
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
