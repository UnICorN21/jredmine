package com.unicorn.domain;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * Created by Huxley on 7/3/15.
 */
@Entity
@DynamicInsert
@Table(name = "issue_user_log", catalog = "jredmine")
public class History implements Serializable {
    private int id;
    private Issue target;
    private User author;
    private Timestamp logTime;
    private String notes;

    private Set<IssueLog> logs;

    public History() { /* null */ }

    public History(Issue target, Set<IssueLog> logs, User author, String note) {
        this.target = target;
        this.logs = logs;
        this.author = author;
        this.notes = note;
        this.logTime = new Timestamp((new Date()).getTime());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id", nullable = false)
    public Issue getTarget() {
        return target;
    }

    public void setTarget(Issue target) {
        this.target = target;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "history")
    public Set<IssueLog> getLogs() {
        return logs;
    }

    public void setLogs(Set<IssueLog> logs) {
        this.logs = logs;
    }

    @Column(name = "time", nullable = false, length = 10)
    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    @Column(name = "notes", nullable = true, length = 255)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String note) {
        this.notes = note;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
