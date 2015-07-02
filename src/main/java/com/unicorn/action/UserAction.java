package com.unicorn.action;

import com.unicorn.bean.UserInfo;
import com.unicorn.domain.User;
import com.unicorn.service.UserService;
import net.sf.json.JSONObject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;

/**
 * Created by Huxley on 6/29/15.
 */
@Namespace("/")
@Scope("prototype")
@ParentPackage("base")
public class UserAction extends BaseAction<User> {

    private static final String USERID = "userId";
    private static final String USERNAME = "username";
    private static final String REDIRECTPATH = "redirectpath";

    private User user;

    @Resource
    private UserService userService;

    public User getModel() {
        if (null == user) user = new User();
        return user;
    }

    @Action(value = "login", results = {
            @Result(name = SUCCESS, location = "/login.jsp"),
            @Result(name = INPUT, location = "/login.jsp")
    })
    public String login() {

        if (null == session.get(REDIRECTPATH)) {
            String redirectPath = request.getHeader("referer");
            session.put(REDIRECTPATH, redirectPath);
        }

        user = userService.userLogin(user.getUsername(), user.getPassword());
        if (null != user) {
            session.put(USERID, user.getId());
            session.put(USERNAME, user.getUsername());

            return SUCCESS;
        }

        return INPUT;
    }

    @Action(value = "logout", results = {
            @Result(name = SUCCESS, location = "/index.jsp")
    })
    public String logout() {
        session.remove(USERID);
        session.remove(USERNAME);

        return SUCCESS;
    }

    @Action(value = "ajax_userinfo", results = @Result(type = "json"))
    public String ajaxUserInfo() throws Exception {
        JSONObject json = JSONObject.fromObject(null == session.get(USERID) ? new UserInfo() : new UserInfo(user));
        response.setContentType("text/json; charset=utf-8");
        response.getWriter().print(json);

        return null;
    }
}
