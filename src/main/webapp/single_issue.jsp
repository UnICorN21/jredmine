<%@ page import="com.opensymphony.xwork2.util.ValueStack" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.unicorn.Utils" %>
<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/1/15
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
  <s:set name="menuSelected" value="'issues'"/>
</head>
<section class="single-issue">
  <aside>
    <h3>Issues</h3>
    <ul>
      <li><a>View all issues</a></li>
      <li><a>Summary</a></li>
      <li><a>Calendar</a></li>
      <li><a>Gantt</a></li>
    </ul>
  </aside>
  <div class="content">
    <s:if test="%{null != #session.userId}">
      <div class="contextual">
        <a class="icon icon-edit" href="">Edit</a>
        <a class="icon icon-time-add" href="">Log time</a>
        <s:if test="true">
          <a class="icon icon-fav-off" href="">Watch</a>
        </s:if>
        <s:else>
          <a class="icon icon-fav" href="">Unwatch</a>
        </s:else>
      </div>
    </s:if>
    <h2>${tracker}&nbsp;&nbsp;#${id}</h2>
    <div class="issue">
      <div class="subject">
        <div class="contextual next-prev-links">
          <s:if test="%{prevIssue != -1}">
            <s:a action="single_issue" namespace="/">
              <s:param name="id" value="prevIssue"/>
              « Previous
            </s:a>
          </s:if>
          <s:else>
            « Previous
          </s:else>
          &nbsp;|&nbsp;<span class="position">${idx}&nbsp;of&nbsp;${project.issues.size()}</span>&nbsp;|&nbsp;
          <s:if test="%{nextIssue != -1}">
            <s:a action="single_issue" namespace="/">
              <s:param name="id" value="nextIssue"/>
              Next »
            </s:a>
          </s:if>
          <s:else>
            Next »
          </s:else>
        </div>
        <s:if test="parent != null">
          <div>
            <p>
              <a href="/single_issue.do?id=${parent.id}">${parent.tracker}&nbsp;&nbsp;#${parent.id}</a>
              ${parent.subject}
            </p>
            <div>
              <h3>${subject}</h3>
            </div>
          </div>
        </s:if>
        <s:else>
          <div>
            <h3>${subject}</h3>
          </div>
        </s:else>
      </div>
      <p class="author">
        Added by
        <s:a action="user">
          <s:param name="id" value="userByAssigner.id"/>
          ${userByAssigner.username}
        </s:a>
        <%
          ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");
          long createtime = ((Timestamp)vs.findValue("createTime", Timestamp.class)).getTime();
          long updatetime = ((Timestamp)vs.findValue("updateTime", Timestamp.class)).getTime();

          String createAgo = Utils.time2ago(createtime);
          String updateAgo = Utils.time2ago(updatetime);
        %>
        <a href="/project/activity.do?from=<%=createtime%>"><%=createAgo%> ago.</a>
        Updated
        <a href="/project/activity.do?from=<%=updatetime%>"><%=updateAgo%> ago.</a>
      </p>
      <table class="attributes">
        <tbody>
        <tr>
          <th class="status">Status:</th>
          <td class="status">${status}</td>
          <th class="start-date">Start date:</th>
          <td class="start-date">${startDate}</td>
        </tr>
        <tr>
          <th class="priority">Priority:</th>
          <td class="priority">${priority}</td>
          <th class="due-date">Due date:</th>
          <td class="due-date">${dueDate}</td>
        </tr>
        <tr>
          <th class="assigned-to">Assignee:</th>
          <td class="assigned-to">${userByAssignee.username}</td>
          <th class="progress">% Done:</th>
          <td class="progress">
            <span class="under"></span>
            <span class="upper"></span>
            <p>${progress}%</p>
          </td>
        </tr>
        <tr>
          <th class="category">Category:</th>
          <td class="category"><s:property default="-" value="category"/></td>
          <th class="spent-time">Spent time:</th>
          <td class="spent-time">-</td>
        </tr>
        <tr>
          <th class="fixed-version">Target version:</th>
          <td class="fixed-version"><s:property default="-" value="targetVersion"/></td>
          <th></th>
          <td></td>
        </tr>
        </tbody>
      </table>
      <hr>
      <div class="description">
        <h3>Description</h3>
        <div>${description}</div>
      </div>
    </div>
    <div id="history">
      <h3>History</h3>
      <s:debug></s:debug>
    </div>
  </div>
  <script src="/static/javascripts/singleissue.js"></script>
</section>


