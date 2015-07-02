package com.unicorn.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Huxley on 6/29/15.
 */
@Controller
@InterceptorRefs({
        @InterceptorRef(value = "modelDriven", params = {"refreshModelBeforeResult", "true"}),
        @InterceptorRef(value = "defaultStack")
})
public class BaseAction<T> extends ActionSupport
        implements ModelDriven<T>, SessionAware, ServletRequestAware, ServletResponseAware {
    protected Map<String, Object> session;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public T getModel() {
        return null;
    }

    public void setSession(Map<String, Object> map) {
        session = map;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        response = httpServletResponse;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        request = httpServletRequest;
    }
}
