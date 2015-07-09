<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/7/15
  Time: 3:17 PM
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
      <a class="icon icon-add" href="/group_new.jsp">New Groups</a>
    </div>
    <h2>Groups</h2>
    <div class="autoscroll">
      <table class="norm list">
        <thead>
        <tr>
          <th>Group</th>
          <th>Users</th>
          <th></th>
        </tr>
        </thead>
        <tbody>
          <s:iterator value="groups" var="group" status="g">
            <tr>
              <td>${group.name}</td>
              <td>${group.count}</td>
              <td class="buttons">
                <s:a action="delete" namespace="/group" class="icon icon-delete">
                  <s:param name="id" value="#group.id"/>
                  Delete
                </s:a>
              </td>
            </tr>
          </s:iterator>
        </tbody>
      </table>
    </div>
  </div>
</div>