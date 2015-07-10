package com.unicorn.bean;

import com.unicorn.domain.Issue;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Huxley on 7/1/15.
 */
public class SimpleIssue {
    private int id;
    private String assignee;
    private String subject;
    private String priority;
    private String status;
    private String tracker;
    private String updateTime;

    private String startDate;
    private String dueDate;


    public SimpleIssue(Issue issue) {
        this.id = issue.getId();
        this.assignee = issue.getAssignee().getUsername();
        this.subject = issue.getSubject();
        this.priority = issue.getPriority().getDesc();
        this.status = issue.getStatus().getDesc();
        this.tracker = issue.getTracker().getName();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.updateTime = df.format(issue.getUpdateTime());
        this.startDate = issue.getStartDate().toString();
        this.dueDate = null == issue.getDueDate() ? (new Date()).toString() : issue.getDueDate().toString();
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTracker() {
        return tracker;
    }

    public void setTracker(String tracker) {
        this.tracker = tracker;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
