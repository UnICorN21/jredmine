<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/6/15
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<head>
  <s:set name="hideMainMenu" value="true"/>
</head>

<div class="main">
  <div class="content">
    <div class="contextual">
      <a class="icon icon-add" href="">New User</a>
    </div>
    <h2>Users</h2>
    <form action="" method="get">
      <fieldset>
        <legend>Filters</legend>
        <label for="status">Status:</label>
        <select id="status">
          <option>active</option>
        </select>
        <label for="name">User:</label>
        <input id="name" size="30" type="text" name="name">
        <input type="submit" value="Apply" class="small">
        <a href="" class="icon icon-reload">Clear</a>
      </fieldset>
    </form>
    <div class="autoscroll">
      <table class="norm list">
        <thead>
        <tr>
          <th>Username</th>
          <th>Email</th>
          <th>Administrator</th>
          <th>Created</th>
          <th>Last Connection</th>
          <th></th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="users" var="user" status="u">
          <s:if test="%{true == #u.odd}">
          <tr class="root odd">
          </s:if>
          <s:else>
          <tr class="root">
          </s:else>
            <td class="name"><span>${user.username}</span></td>
            <td class="email"><a href="mailto: ${user.email}">${user.email}</a></td>
            <td class="tick">
              <s:if test="%{@com.unicorn.domain.User$Role@ADMIN == #user.role}">
                <img src="/static/images/toggle_check.png" alt="Toggle check">
              </s:if>
            </td>
            <td class="created_on">${user.registerTime}</td>
            <td class="last_login_on">${user.lastConnectionTime}</td>
            <td class="buttons">
              <a class="icon icon-lock" href="">Lock</a>
              <a class="icon icon-delete" href="">Delete</a>
            </td>
          </tr>
        </s:iterator>
        </tbody>
      </table>
    </div>
  </div>
</div>
