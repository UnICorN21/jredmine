package com.unicorn.domain;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Huxley on 7/3/15.
 */
@Entity
@DynamicInsert
@Table(name = "issue_edit_log", catalog = "jredmine")
public class IssueLog implements Serializable {
    private int id;
    private History history;
    private String editProperty;
    private String oldValue;
    private String newValue;

    public IssueLog() { /* null */ }

    public IssueLog(History history, String editProperty, String oldValue, String newValue) {
        this.history = history;
        this.editProperty = editProperty;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Column(name = "edit_property", nullable = false, length = 255)
    public String getEditProperty() {
        return editProperty;
    }

    public void setEditProperty(String editProperty) {
        this.editProperty = editProperty;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iulog_id", nullable = false)
    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "new_value", nullable = false, length = 255)
    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Column(name = "old_value", length = 255)
    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }
}
