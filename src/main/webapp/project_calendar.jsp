<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/1/15
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
  <s:set name="menuSelected" value="'calendar'"/>
  <link rel="stylesheet" type="text/css" href="/static/stylesheets/fullcalendar.min.css">
  <script src="/static/javascripts/moment.min.js"></script>
  <script src="/static/javascripts/fullcalendar.min.js"></script>
</head>

<section class="calendar">
  <aside>
    <h3>Issues</h3>
    <ul>
      <li><a>View all issues</a></li>
      <li><a>Summary</a></li>
      <li><a>Calendar</a></li>
      <li><a>Gantt</a></li>
    </ul>
  </aside>
  <div class="content" style="display:block; overflow: hidden;">
    <h2>Calendar</h2>
    <div id="calendar"></div>
  </div>
  <script src="/static/javascripts/calendar.js"></script>
</section>
