package com.unicorn.bean;

import com.unicorn.domain.Issue;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Huxley on 7/3/15.
 */
public class FormIssue {
    private Integer id;
    private String projectId;
    private Integer parentId;
    private Integer trackerId;
    private String subject;
    private Issue.Status status;
    private Issue.Priority priority;
    private String assigneeId;
    private Date startDate;
    private Date dueDate;
    private Double estimatedTime;
    private Integer progress;
    private String description;

    public FormIssue() { /* null */ }

    public FormIssue(Issue issue) {
        this.id = issue.getId();
        this.projectId = issue.getProject().getId();
        this.parentId = null != issue.getParent() ? issue.getParent().getId() : null;
        this.trackerId = issue.getTracker().getId();
        this.subject = issue.getSubject();
        this.status = issue.getStatus();
        this.priority = issue.getPriority();
        this.assigneeId = issue.getUserByAssignee().getId();
        this.startDate = issue.getStartDate();
        this.dueDate = issue.getDueDate();
        this.estimatedTime = issue.getEstimatedTime();
        this.progress = issue.getProgress();
        this.description = issue.getDescription();
    }

    public FormIssue(String assigneeId, Date dueDate, Double estimatedTime, Integer id, Issue.Priority priority, Integer progress, String projectId, Integer parentId, Date startDate, Issue.Status status, String subject, Integer trackerId, String description) {
        this.assigneeId = assigneeId;
        this.dueDate = dueDate;
        this.estimatedTime = estimatedTime;
        this.id = id;
        this.priority = priority;
        this.progress = progress;
        this.projectId = projectId;
        this.parentId = parentId;
        this.startDate = startDate;
        this.status = status;
        this.subject = subject;
        this.trackerId = trackerId;
        this.description = description;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.dueDate = df.parse(dueDate);
    }

    public Double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Issue.Priority getPriority() {
        return priority;
    }

    public void setPriority(Issue.Priority priority) {
        this.priority = priority;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.startDate = df.parse(startDate);
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

    public Integer getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(Integer trackerId) {
        this.trackerId = trackerId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
