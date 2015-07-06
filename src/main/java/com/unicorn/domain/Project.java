package com.unicorn.domain;

import javafx.util.Pair;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * Project entity. @author MyEclipse Persistence Tools
 */
@Entity
@DynamicInsert
@Table(name = "project", catalog = "jredmine")
public class Project implements java.io.Serializable {

	// Fields

	private String id;
	private User userByManager;
	private Project parent;
	private String name;
	private Timestamp createTime;
	private boolean closed;
	private Set<Project> projects = new HashSet<Project>(0);
	private Set<Issue> issues = new HashSet<Issue>(0);
	private Set<User> developers = new HashSet<User>(0);
	private Set<Category> categories = new HashSet<Category>(0);
	private Set<TargetVersion> targetVersions = new HashSet<TargetVersion>(0);

	private Map<Issue.Tracker, List<Issue>> trackeredIssues = new HashMap<Issue.Tracker, List<Issue>>();

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** minimal constructor */
	public Project(User manager, String name, Timestamp createTime) {
		this.userByManager = manager;
		this.name = name;
		this.createTime = createTime;
	}

	/** full constructor */
	public Project(Set<Category> categories, boolean closed, Timestamp createTime, Set<User> developers, String id, Set<Issue> issues, String name, Project parent, Set<Project> projects, Set<TargetVersion> targetVersions, User userByManager) {
		this.categories = categories;
		this.closed = closed;
		this.createTime = createTime;
		this.developers = developers;
		this.id = id;
		this.issues = issues;
		this.name = name;
		this.parent = parent;
		this.projects = projects;
		this.targetVersions = targetVersions;
		this.userByManager = userByManager;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Type(type = "yes_no")
	@Column(name = "closed", nullable = false, length = 1, columnDefinition = "varchar(1) default 'N'")
	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager", nullable = false)
	public User getUserByManager() {
		return this.userByManager;
	}

	public void setUserByManager(User manager) {
		this.userByManager = manager;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")
	public Project getParent() {
		return this.parent;
	}

	public void setParent(Project parent) {
		this.parent = parent;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "project_user_map", joinColumns = { @JoinColumn(name = "projectid") },
	inverseJoinColumns = { @JoinColumn(name = "userid") })
	public Set<User> getDevelopers() {
		return developers;
	}

	public void setDevelopers(Set<User> developers) {
		this.developers = developers;
	}


	public List<Issue> getTrackeredIssues(Issue.Tracker tracker) {
		if (null != trackeredIssues.get(tracker)) return trackeredIssues.get(tracker);
		List<Issue> ans = new ArrayList<Issue>();
		for (Issue issue: issues) {
			if (tracker == issue.getTracker()) ans.add(issue);
		}
		trackeredIssues.put(tracker, ans);
		return ans;
	}

	public Pair<Integer, Integer> getTrackeredIssuesStatic(Issue.Tracker tracker) {
		List<Issue> issues = getTrackeredIssues(tracker);
		int left = 0, right = 0;
		for (Issue issue: issues) {
			if (Issue.Status.Closed != issue.getStatus()) ++left;
			++right;
		}
		return new Pair<Integer, Integer>(left, right);
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<TargetVersion> getTargetVersions() {
		return targetVersions;
	}

	public void setTargetVersions(Set<TargetVersion> targetVersions) {
		this.targetVersions = targetVersions;
	}
}