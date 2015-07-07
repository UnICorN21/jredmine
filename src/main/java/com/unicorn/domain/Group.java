package com.unicorn.domain;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Huxley on 7/7/15.
 */
@Entity
@DynamicInsert
@Table(name = "group", catalog = "jredmine")
public class Group implements Serializable {
    private int id;
    private String name;
    private int count;
    private Set<User> users;

    public Group() { /* null */ }

    @Column(name = "count", nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_user_map", joinColumns = { @JoinColumn(name = "gid") },
            inverseJoinColumns = { @JoinColumn(name = "uid") })
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
