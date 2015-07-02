<%@ page import="com.opensymphony.xwork2.util.ValueStack" %>
<%@ page import="com.unicorn.domain.Issue" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.unicorn.domain.Project" %>
<%@ page import="javafx.util.Pair" %>
<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/1/15
  Time: 2:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
  <s:set name="menuSelected" value="'overview'"/>
</head>
<section class="overview">
  <aside>
    <h3>Spent time</h3>
    <p>hours</p>
    <p>
      <a href="/project/spent_time?tab=detail">Details</a>&nbsp;|&nbsp;
      <a href="/project/spent_time?tab=report">Report</a>
    </p>
  </aside>
  <div class="content">
    <h2>Overview</h2>
    <div class="left">
      <div class="issues box">
        <h3>Issue tracking</h3>
        <%
          ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");
          Project project = (Project)vs.getRoot().get(0);
          Pair<Integer, Integer> featurePair = project.getTrackeredIssuesStatic(Issue.Tracker.Feature);
          Pair<Integer, Integer> bugPair = project.getTrackeredIssuesStatic(Issue.Tracker.Bug);
          Pair<Integer, Integer> supportPair = project.getTrackeredIssuesStatic(Issue.Tracker.Support);
        %>
        <ul>
          <li>
            <a href="/project/issues?set_filter=feature">Feature</a>:
            <%=featurePair.getKey()%>&nbsp;open&nbsp;/&nbsp;<%=featurePair.getValue()%>
          </li>
          <li>
            <a href="/project/issues?set_filter=bug">Bug</a>:
            <%=bugPair.getKey()%>&nbsp;open&nbsp;/&nbsp;<%=bugPair.getValue()%>
          </li>
          <li>
            <a href="/project/issues?set_filter=support">Support</a>:
            <%=supportPair.getKey()%>&nbsp;open&nbsp;/&nbsp;<%=supportPair.getValue()%>
          </li>
        </ul>
        <p>
          <a href="/project/issues">View all issues</a> &nbsp;|&nbsp;
          <a href="/project/calendar">Calendar</a>&nbsp;|&nbsp;
          <a href="/project/gantt">Gantt</a>
        </p>
      </div>
    </div>
    <div class="right">
      <div class="member box">
        <h3>Members</h3>
        <p>
          <span class="label">Manager:</span>
          <s:a action="user" class="user" namespace="/">
            <s:param name="id" value="userByManager.id"/>
            <s:property value="userByManager.username"/>
          </s:a>
        </p>
        <p>
          <span class="label">Developer:</span>
          <s:iterator value="developers" var="developer">
            <s:a action="user" class="user" namespace="/">
              <s:param name="id" value="#developer.id"/>
              <s:property value="#developer.username"/>
            </s:a>
          </s:iterator>
        </p>
      </div>
    </div>
  </div>
</section>

