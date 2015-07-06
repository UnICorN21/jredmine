<%@ page import="com.opensymphony.xwork2.ognl.OgnlValueStack" %>
<%@ page import="com.opensymphony.xwork2.util.ValueStack" %>
<%@ page import="com.unicorn.domain.Project" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<head>
    <s:set name="hideMainMenu" value="true"/>
</head>
<!-- Content starts here. -->
<div class="main" style="padding: 6px 10px 10px 10px; background: #fff;">
    <h2>Home</h2>
    <div class="left">

    </div>
    <div class="right">
        <div class="project box">
            <h3>Latest Projects</h3>
            <s:action name="stub" namespace="/project" var="projectStub" executeResult="false"/>
            <ul>
                <s:iterator value="#projectStub.latestProjects" var="project">
                    <%
                        ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");
                        Project project = (Project)((OgnlValueStack) vs).findValue("project", Project.class);
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String createTime = df.format(project.getCreateTime());
                    %>
                    <li>
                        <s:a action="overview" namespace="/project">
                            <s:param name="id" value="#project.id"/>
                            <s:property value="#project.name"/>
                        </s:a>
                        &nbsp;(<%=createTime%>)
                    </li>
                </s:iterator>
            </ul>
        </div>
    </div>
</div>