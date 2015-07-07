<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/6/15
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<head>
  <s:set name="hideMainMenu" value="true"/>
</head>

<div class="main">
  <div class="content" style="width: 100%">
    <h2>Administration</h2>
    <ul id="admin-menu">
      <li><s:a action="projects" namespace="/admin" class="projects">Projects</s:a></li>
      <li><s:a action="users" namespace="/admin" class="users">Users</s:a></li>
      <li><s:a action="groups" namespace="/admin" class="groups">Groups</s:a></li>
    </ul>
  </div>
</div>
