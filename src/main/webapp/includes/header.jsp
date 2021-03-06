<%@ page import="com.unicorn.Utils" %>
<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 6/29/15
  Time: 9:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<s:set name="menuItems" value="{'overview', 'activity', 'issues', 'new', 'gantt', 'calendar',
      'news', 'documents', 'wiki', 'files', 'settings'}"/>

<html>
<body>
  <header>
    <div class="top-menu">
      <div>
        <ul>
          <li><a href="/">Home</a></li>
          <security:authorize access="hasRole('ROLE_USER')">
            <li><a href="">My Page</a></li>
          </security:authorize>
          <li>
            <s:a action="list" namespace="/project">
              <s:param name="clozed" value="false"/>
              Projects
            </s:a>
          </li>
          <security:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="/admin.jsp">Administration</a></li>
          </security:authorize>
          <li><a href="http://www.redmine.org/guide">Help</a></li>
        </ul>
      </div>
      <div class="account">
        <ul>
          <security:authorize access="hasRole('ROLE_USER')">
            <%
              String username = Utils.getCurrentUser().getUsername();
            %>
            <li><a href="#">Logged in as <%=username%></a></li>
            <li><a href="">My account</a></li>
            <li><s:a action="logout" namespace="/">Sign out</s:a></li>
          </security:authorize>
          <security:authorize access="isAnonymous()">
            <li><a href="/login.jsp">Sign in</a></li>
            <li><a href="/register.jsp">Register</a></li>
          </security:authorize>
        </ul>
      </div>
    </div>
    <div class="banner">
      <div class="quick-search">
        <form action="/search.do" method="get">
          <label for="qs">
            <a accesskey="4" href="/search.do">Search</a>
          </label>
          <input type="text" name="qs" id="qs" class="small" size="20" accesskey="f">
        </form>
        <security:authorize access="hasRole('ROLE_USER')">
          <select name="project_quick_jump_box" id="project_quick_jump_box" onchange="if (this.value != '') { window.location = this.value; }">
            <option value>Jump to a project...</option>
            <option value disabled>---</option>
          </select>
        </security:authorize>
      </div>
      <h1>
        <s:if test="%{null != #hideMainMenu && null != #session.currentProject}">${session.currentProject.name}</s:if>
        <s:else>JRedmine</s:else>
      </h1>
      <s:if test="!#hideMainMenu">
        <div class="main-menu">
          <ul>
            <s:action name="gen_menu" namespace="/" var="src" executeResult="false"/>
            <s:iterator value="#src.menus" var="item">
              <s:if test="%{#menuSelected == #item}">
                <li class="selected">
              </s:if>
              <s:else>
                <li>
              </s:else>
                  <s:a action="%{item}" namespace="/project" includeParams="none">
                    <s:param name="id" value="#session.currentProject.id"/>
                    ${item}
                  </s:a>
                </li>
            </s:iterator>
         </ul>
        </div>
      </s:if>
    </div>
  </header>
  <script src="/static/javascripts/util.js"></script>
  <script src="/static/javascripts/header.js"></script>
</body>
</html>
