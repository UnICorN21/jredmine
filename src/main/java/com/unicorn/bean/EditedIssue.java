package com.unicorn.bean;

import com.unicorn.domain.Issue;

/**
 * Created by Huxley on 7/3/15.
 */
public class EditedIssue {
    private String projectId;
    private Issue.Tracker tracker;
    private String subject;
    private Issue.Status status;
    private Issue.Priority priority;
    private String assigneeId;
    private String startDate;
    private String dueDate;
    private String estimatedTime;
    private String progress;

    private double logHours;
    private String activityId;
    private String comments;

    private String notes;

    public EditedIssue() { /* null */ }

    public EditedIssue(String activityId, String assigneeId, String comments, String dueDate, String estimatedTime, double logHours, String notes, Issue.Priority priority, String progress, String projectId, String startDate, Issue.Status status, String subject, Issue.Tracker tracker) {
        this.activityId = activityId;
        this.assigneeId = assigneeId;
        this.comments = comments;
        this.dueDate = dueDate;
        this.estimatedTime = estimatedTime;
        this.logHours = logHours;
        this.notes = notes;
        this.priority = priority;
        this.progress = progress;
        this.projectId = projectId;
        this.startDate = startDate;
        this.status = status;
        this.subject = subject;
        this.tracker = tracker;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public double getLogHours() {
        return logHours;
    }

    public void setLogHours(double logHours) {
        this.logHours = logHours;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Issue.Priority getPriority() {
        return priority;
    }

    public void setPriority(Issue.Priority priority) {
        this.priority = priority;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Issue.Status getStatus() {
        return status;
    }

    public void setStatus(Issue.Status status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Issue.Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Issue.Tracker tracker) {
        this.tracker = tracker;
    }
}
