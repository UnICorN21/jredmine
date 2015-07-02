package com.unicorn.bean;

import com.unicorn.domain.User;

import java.text.SimpleDateFormat;

/**
 * Created by Huxley on 7/2/15.
 */
public class UserInfo {
    private String id;
    private String username;
    private String email;
    private String registerTime;
    private String lastConnectionTime;

    public UserInfo() { /* null */ }

    public UserInfo(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = (1 == user.getEmailHidden()) ? null : user.getEmail();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.registerTime = df.format(user.getRegisterTime());
        this.lastConnectionTime = df.format(user.getLastConnectionTime());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastConnectionTime() {
        return lastConnectionTime;
    }

    public void setLastConnectionTime(String lastConnectionTime) {
        this.lastConnectionTime = lastConnectionTime;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
