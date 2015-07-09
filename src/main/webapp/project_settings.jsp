<%@ page import="com.opensymphony.xwork2.util.ValueStack" %>
<%@ page import="com.unicorn.action.ProjectAction" %>
<%@ page import="com.unicorn.domain.Project" %>
<%@ page import="com.unicorn.domain.Tracker" %>
<%@ page import="com.unicorn.domain.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/1/15
  Time: 10:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
  <s:set name="menuSelected" value="'settings'"/>
  <script src="/static/javascripts/jquery-ui.min.js"></script>
</head>

<section class="settings">
  <div class="content" style="width: 100%">
    <h2>Settings</h2>
    <%
      ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");
      Project currentProject = (Project) session.getAttribute(ProjectAction.CURRENT_PROJECT);
    %>
    <div id="tabs">
      <ul>
        <li><a href="#information">Information</a></li>
        <li><a href="#versions">Versions</a></li>
      </ul>
      <div id="information">
        <form action="/project/edit.do" method="post">
          <input type="hidden" name="id" value="${session.currentProject.id}">
          <div class="box tabular">
            <p>
              <label for="project_name">
                Name
                <span class="required">*</span>
              </label>
              <input id="project_name" type="text" size="60" name="name" value="${session.currentProject.name}">
            </p>
            <p>
              <label for="project_description">Description</label>
              <textarea id="project_description" rows="4" cols="58" name="description">
              ${session.currentProject.description}
              </textarea>
            </p>
            <p>
              <label for="project_public">Public</label>
              <input id="project_public" name="isPublic" type="checkbox" <s:if test="%{#session.currentProject.isPublic}">checked</s:if>>
            </p>
            <p>
              <s:action name="users" namespace="/admin" var="src" executeResult="false"/>
              <label for="project_manager">Manager</label>
              <s:select id="project_manager" name="managerId" list="#src.users"
                      headerKey="%{#session.currentProject.userByManager.id}"
                      headerValue="%{#session.currentProject.userByManager.username}"
                      listValue="username" listKey="id"/>
            </p>
            <p>
              <s:action name="list" namespace="/project" var="projects" executeResult="false"/>
              <label for="project_parent">Subproject of</label>
              <s:select id="project_parent" name="parentId"
                        list="#projects.projectList" headerKey="%{#session.currentProject.parent.name}"
                        headerValue="%{#session.currentProject.parent.id}"
                        listValue="name" listKey="id"/>
            </p>
            <p>
              <s:checkbox name="inherit" fieldValue="true" label="Inherit members"/>
            </p>
          </div>
          <fieldset class="box tabular trackers">
            <legend>Trackers</legend>
            <s:action name="trackers" namespace="/admin" var="src" executeResult="false"/>
            <s:iterator value="#src.trackers" var="tracker" status="t">
              <label class="floating">
                <%
                  Tracker tracker = (Tracker)vs.getRoot().get(0);
                  boolean flag = currentProject.getTrackers().contains(tracker);
                  if (flag) {
                %>
                <input name="projectTrackers" type="checkbox" value="${tracker.id}" checked>
                <%
                  } else {
                %>
                <input name="projectTrackers" type="checkbox" value="${tracker.id}">
                <%
                  }
                %>
                ${tracker.name}
              </label>
            </s:iterator>
          </fieldset>
          <fieldset class="box tabular developers">
            <legend>Developers</legend>
            <s:action name="users" namespace="/admin" var="src" executeResult="false"/>
            <s:iterator value="#src.users" var="user" status="u">
              <label class="floating">
              <%
                User user = (User) vs.getRoot().get(0);
                boolean flag = currentProject.getDevelopers().contains(user);
                if (flag) {
              %>
              <input name="developerIds" type="checkbox" value="${user.id}" checked>
              <%
                } else {
              %>
              <input name="developerIds" type="checkbox" value="${user.id}">
              <%
                }
              %>
              ${user.username}
              </label>
            </s:iterator>
          </fieldset>
          <input type="submit" value="save">
        </form>
      </div>
      <div id="versions"></div>
    </div>
  </div>
  <script>
    $('#tabs').tabs();
  </script>
</section>
