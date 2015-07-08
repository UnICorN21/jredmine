<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/8/15
  Time: 4:55 PM
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
      <a class="icon icon-add" href="/tracker_new.jsp">New Tracker</a>
    </div>
    <h2>Trackers</h2>
    <div class="autoscroll">
      <table class="list">
        <thead>
        <tr>
          <th>Tracker</th>
          <th></th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="trackers" var="tracker" status="t">
          <tr>
            <td>${tracker.name}</td>
            <td class="buttons">
              <s:a action="delete" namespace="/tracker" class="icon icon-delete">
                <s:param name="id" value="#tracker.id"/>
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
