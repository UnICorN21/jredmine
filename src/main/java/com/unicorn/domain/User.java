package com.unicorn.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@DynamicInsert
@Table(name = "user", catalog = "jredmine", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements java.io.Serializable, UserDetails {
	// Fields

	private String id;
	private String username;
	private String password;
	private String email;
	private Integer emailHidden = 1;
	private Timestamp registerTime = new Timestamp((new Date()).getTime());
	private Timestamp lastConnectionTime = new Timestamp((new Date()).getTime());
	private Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>(0);
	private Set<Issue> issuesForAssignee = new HashSet<Issue>(0);
	private Set<Issue> issuesForAssigner = new HashSet<Issue>(0);
	private Set<Project> projectsByManager = new HashSet<Project>(0);
	private Set<Project> developProjects = new HashSet<Project>(0);
	private Set<Group> groups = new HashSet<Group>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, String email,
			Timestamp registerTime, Timestamp lastConnectionTime) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.registerTime = registerTime;
		this.lastConnectionTime = lastConnectionTime;
	}

	/** full constructor */
	public User(String username, String password, String email,
			Integer emailHidden, Timestamp registerTime,
			Timestamp lastConnectionTime, Set<Issue> issuesForAssignee,
			Set<Issue> issuesForAssigner, Set<Project> projectsByManager) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.emailHidden = emailHidden;
		this.registerTime = registerTime;
		this.lastConnectionTime = lastConnectionTime;
		this.issuesForAssignee = issuesForAssignee;
		this.issuesForAssigner = issuesForAssigner;
		this.projectsByManager = projectsByManager;
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

	@Column(name = "username", unique = true, nullable = false)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", nullable = false, length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "email_hidden", nullable = false)
	public Integer getEmailHidden() {
		return this.emailHidden;
	}

	public void setEmailHidden(Integer emailHidden) {
		this.emailHidden = emailHidden;
	}

	@Column(name = "register_time", nullable = false, length = 19)
	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "last_connection_time", nullable = false, length = 19)
	public Timestamp getLastConnectionTime() {
		return this.lastConnectionTime;
	}

	public void setLastConnectionTime(Timestamp lastConnectionTime) {
		this.lastConnectionTime = lastConnectionTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByAssignee")
	public Set<Issue> getIssuesForAssignee() {
		return this.issuesForAssignee;
	}

	public void setIssuesForAssignee(Set<Issue> issuesForAssignee) {
		this.issuesForAssignee = issuesForAssignee;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByAssigner")
	public Set<Issue> getIssuesForAssigner() {
		return this.issuesForAssigner;
	}

	public void setIssuesForAssigner(Set<Issue> issuesForAssigner) {
		this.issuesForAssigner = issuesForAssigner;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByManager")
	public Set<Project> getProjectsByManager() {
		return this.projectsByManager;
	}

	public void setProjectsByManager(Set<Project> projectsByManager) {
		this.projectsByManager = projectsByManager;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "developers")
	public Set<Project> getDevelopProjects() {
		return developProjects;
	}

	public void setDevelopProjects(Set<Project> developProjects) {
		this.developProjects = developProjects;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	public Set<GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void setGrantedAuthorities(Set<GrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	public boolean isEnabled() {
		return true;
	}
}