package com.unicorn.domain;

import com.unicorn.bean.FormIssue;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Issue entity. @author MyEclipse Persistence Tools
 */
@Entity
@DynamicInsert
@Table(name = "issue", catalog = "jredmine")
public class Issue implements java.io.Serializable {
	public enum Status {
		New("New"), In_Progress("In progress"), Resolved("Resolved"), Feedback("Feedback"), Closed("Closed");

		private String desc;

		private Status(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}
	public enum Priority {
		Normal("Normal"), Low("Low"), High("High"), Urgent("Urgent"), Immediate("Immediate");

		private String desc;

		private Priority(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

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
	private Double estimatedTime;
	private Integer progress;
	private String category;
	private Integer targetVersion;
	private Set<Issue> issues = new HashSet<Issue>(0);
	private Set<History> histories = new HashSet<History>(0);
	private Set<LogTime> logTimes = new HashSet<LogTime>(0);

	private double spentTime;

	private int idx;
	private int prevIssue;
	private int nextIssue;

	// Constructors

	/** default constructor */
	public Issue() { /* null */ }

	public Issue(FormIssue formIssue, User author) {
		this.userByAssigner = author;
		this.userByAssignee = new User();
		this.userByAssignee.setId(formIssue.getAssigneeId());
		this.project = new Project();
		this.project.setId(formIssue.getProjectId());
		this.subject = formIssue.getSubject();
		this.priority = formIssue.getPriority();
		this.status = formIssue.getStatus();
		this.tracker = new Tracker();
		this.tracker.setId(formIssue.getTrackerId());
		this.createTime = new Timestamp((new Date()).getTime());
		this.updateTime = this.createTime;
		this.startDate = formIssue.getStartDate();
		this.dueDate = formIssue.getDueDate();
		this.progress = formIssue.getProgress();
		if (null != formIssue.getParentId()) {
			this.parent = new Issue();
			this.parent.setId(formIssue.getParentId());
		}
		this.description = formIssue.getDescription();
		this.estimatedTime = formIssue.getEstimatedTime();
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
			Timestamp createTime, Timestamp updateTime, Double estimatedTime, Date startDate,
			Date dueDate, Integer progress, String category,
			Integer targetVersion, Set<Issue> issues, Set<LogTime> logTimes) {
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
		this.estimatedTime = estimatedTime;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.progress = progress;
		this.category = category;
		this.targetVersion = targetVersion;
		this.issues = issues;
		this.logTimes = logTimes;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tracker", nullable = false)
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

	@Column(name = "estimated_time", length = 5)
	public Double getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(Double estimatedTime) {
		this.estimatedTime = estimatedTime;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "target")
	@OrderBy("logTime")
	public Set<History> getHistories() {
		return histories;
	}

	public void setHistories(Set<History> histories) {
		this.histories = histories;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "issue")
	public Set<LogTime> getLogTimes() {
		return logTimes;
	}

	public void setLogTimes(Set<LogTime> logTimes) {
		this.logTimes = logTimes;
	}

	@Transient
	public double getSpentTime() {
		if (null != logTimes && 0 != logTimes.size()) {
			for (LogTime logTime: logTimes) {
				spentTime += logTime.getSpentTime();
			}
		}
		return spentTime;
	}

	public void setSpentTime(double spentTime) {
		this.spentTime = spentTime;
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