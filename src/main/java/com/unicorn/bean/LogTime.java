package com.unicorn.bean;

/**
 * Created by Huxley on 7/3/15.
 */
public class LogTime {
    private double logHours;
    private String activityId;
    private String comments;

    public LogTime() { /* null */ }

    public LogTime(String activityId, String comments, double logHours) {
        this.activityId = activityId;
        this.comments = comments;
        this.logHours = logHours;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getLogHours() {
        return logHours;
    }

    public void setLogHours(double logHours) {
        this.logHours = logHours;
    }
}
