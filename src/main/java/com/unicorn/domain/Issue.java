package com.unicorn.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

/**
 * Issue entity. @author MyEclipse Persistence Tools
 */
@Entity
@DynamicInsert
@Table(name = "issue", catalog = "jredmine")
public class Issue implements java.io.Serializable {
	public enum Tracker { Feature, Bug, Support }
	public enum Status { New, In_Progress, Resolved, Feedback, Closed }
	public enum Priority { Normal, Low, High, Urgent, Immediate }

	// Fields

	private int id;
	private User userByAssigner;
	private Issue parent;
	private Project project;
	private User userByAssignee;
	private String subject;
	private String description;
	private Priority priority;
	private Status status;
	private Tracker tracker;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Date startDate;
	private Date dueDate;
	private Integer progress;
	private String category;
	private Integer targetVersion;
	private Set<Issue> issues = new HashSet<Issue>(0);

	private int idx;
	private int prevIssue;
	private int nextIssue;

	// Constructors

	/** default constructor */
	public Issue() {
	}

	/** minimal constructor */
	public Issue(User userByAssigner, Project project, User userByAssignee,
			String subject, Priority priority, Status status, Tracker tracker,
			Timestamp createTime, Timestamp updateTime, Date startDate,
			Integer progress) {
		this.userByAssigner = userByAssigner;
		this.project = project;
		this.userByAssignee = userByAssignee;
		this.subject = subject;
		this.priority = priority;
		this.status = status;
		this.tracker = tracker;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.startDate = startDate;
		this.progress = progress;
	}

	/** full constructor */
	public Issue(User userByAssigner, Issue parent, Project project,
			User userByAssignee, String subject, String description,
			Priority priority, Status status, Tracker tracker,
			Timestamp createTime, Timestamp updateTime, Date startDate,
			Date dueDate, Integer progress, String category,
			Integer targetVersion, Set<Issue> issues) {
		this.userByAssigner = userByAssigner;
		this.parent = parent;
		this.project = project;
		this.userByAssignee = userByAssignee;
		this.subject = subject;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.tracker = tracker;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.progress = progress;
		this.category = category;
		this.targetVersion = targetVersion;
		this.issues = issues;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 11)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assigner", nullable = false)
	public User getUserByAssigner() {
		return this.userByAssigner;
	}

	public void setUserByAssigner(User userByAssigner) {
		this.userByAssigner = userByAssigner;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")
	public Issue getParent() {
		return this.parent;
	}

	public void setParent(Issue parent) {
		this.parent = parent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectid", nullable = false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee", nullable = false)
	public User getUserByAssignee() {
		return this.userByAssignee;
	}

	public void setUserByAssignee(User userByAssignee) {
		this.userByAssignee = userByAssignee;
	}

	@Column(name = "subject", nullable = false)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "priority", nullable = false)
	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tracker", nullable = false)
	public Tracker getTracker() {
		return this.tracker;
	}

	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", nullable = false, length = 19)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false, length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date", length = 10)
	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name = "progress", nullable = false)
	public Integer getProgress() {
		return this.progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	@Column(name = "category")
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "target_version")
	public Integer getTargetVersion() {
		return this.targetVersion;
	}

	public void setTargetVersion(Integer targetVersion) {
		this.targetVersion = targetVersion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
	public Set<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	@Transient
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	@Transient
	public int getNextIssue() {
		return nextIssue;
	}

	public void setNextIssue(int nextIssue) {
		this.nextIssue = nextIssue;
	}

	@Transient
	public int getPrevIssue() {
		return prevIssue;
	}

	public void setPrevIssue(int prevIssue) {
		this.prevIssue = prevIssue;
	}
}