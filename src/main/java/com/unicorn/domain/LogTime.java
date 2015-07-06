package com.unicorn.domain;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Huxley on 7/3/15.
 */
@Entity
@DynamicInsert
@Table(name = "logtime", catalog = "jredmine")
public class LogTime implements Serializable {
    public enum ActivityType {
        Design("Design"), Development("Development"), Requirement("Requirement");

        private String desc;
        private ActivityType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    private int id;
    private User user;
    private Issue issue;
    private Double spentTime;
    private ActivityType activityType;
    private String comments;
    private Timestamp createTime = new Timestamp((new Date()).getTime());

    public LogTime() { /* null */ }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type", nullable = false, length = 1)
    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iid", nullable = false)
    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "comments", nullable = true)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Column(name = "spent_time", nullable = false)
    public Double getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(Double spentTime) {
        this.spentTime = spentTime;
    }
}
