<%@ page import="com.unicorn.Utils" %>
<%@ page import="com.unicorn.action.ProjectAction" %>
<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/6/15
  Time: 5:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
  <s:set name="hideMainMenu" value="true"/>
</head>

<div class="main">
  <div class="content" style="width: 100%">
    <h2>New Project</h2>
    <%
      Object updateFlag = Utils.getSessionAttrAndRemoved(session, ProjectAction.PROJECT_CREATE_SUCCESS_FLAG);
      if (null != updateFlag) {
        if ((boolean)updateFlag) {
    %>
    <div class="flash notice-succeed">Project <strong>${name}</strong> created.</div>
    <%
    } else {
    %>
    <div class="flash notice-failed">Project create failed.</div>
    <%
        }
      }
    %>
    <form class="new" id="new_project" action="/project/create.do" method="post">
      <div class="box tabular">
        <p>
          <label for="project_name">
            Name
            <span class="required">*</span>
          </label>
          <input id="project_name" size="60" type="text" name="name">
        </p>
        <p>
          <label for="project_description">Description</label>
          <textarea id="project_description" name="description" rows="6" cols="58" wrap="hard"></textarea>
        </p>
        <p>
          <s:checkbox name="isPublic" fieldValue="true" label="Public" value="true"/>
        </p>
        <p>
          <s:action name="list" namespace="/project" var="projects" executeResult="false"/>
          <label for="project_parent">Subproject of</label>
          <s:select id="project_parent" name="parentId"
                  list="#projects.projectList" headerKey="" headerValue=""
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
          <s:if test="%{#t.index < 3}">
            <label class="floating">
              <input name="projectTrackers" type="checkbox" value="${tracker.id}" checked>
              ${tracker.name}
            </label>
          </s:if>
          <s:else>
            <label class="floating">
              <input name="projectTrackers" type="checkbox" value="${tracker.name}">
              ${tracker.name}
            </label>
          </s:else>
        </s:iterator>
      </fieldset>
      <fieldset class="box tabular developers">
        <legend>Developers</legend>
        <s:action name="users" namespace="/admin" var="src" executeResult="false"/>
        <s:iterator value="#src.users" var="user">
          <label class="floating">
            <input name="developerIds" type="checkbox" value="${user.id}">
            ${user.username}
          </label>
        </s:iterator>
      </fieldset>
      <input type="submit" name="submitType" value="Create">
      <input type="submit" name="submitType" value="Create and continue">
    </form>
  </div>
  <script>
    util.checkForm('new_project');
    $('#new_project').validate({
      rules: {
        name: "required"
      },
      messages: {
        name: "please enter a name"
      }
    });
    util.dismiss('div.flash');
  </script>
</div>
