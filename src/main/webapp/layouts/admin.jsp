<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/6/15
  Time: 6:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
  <jsp:include page="../includes/head.jsp"/>
</head>
<body>
<decorator:head/>
<jsp:include page="../includes/header.jsp"/>
<div class="main">
  <aside>
    <h3>Administration</h3>
    <div id="admin-menu">
      <ul>
        <li>
          <a class="projects selected" href="/admin/projects.do">Projects</a>
        </li>
        <li>
          <a class="users" href="/admin/users.do">Users</a>
        </li>
        <li>
          <a class="groups" href="/admin/groups.do">Groups</a>
        </li>
        <li>
          <a class="trackers" href="/admin/trackers.do">Trackers</a>
        </li>
      </ul>
    </div>
  </aside>
  <decorator:body/>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
