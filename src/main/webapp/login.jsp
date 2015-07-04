<%@ page import="com.unicorn.Utils" %>
<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 6/29/15
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<head>
  <s:set name="hideMainMenu" value="true"/>
</head>

<div class="main">
  <s:if test="%{null != #session.user.id}">
    <%
      String redirectPath = (String)Utils.getSessionAttrAndRemoved(session, "redirectpath");
      if (!redirectPath.contains("login.do")) response.sendRedirect(redirectPath);
      else {
    %>
    <div class="login-form">
      <p>So where are you from ?</p>
    </div>
    <%
      }
    %>
  </s:if>
  <s:else>
    <form action="/login.do" method="post">
      <div class="login-form">
        <p><span>Login:</span><input name="username" type="text"></p>
        <p><span>Password:</span><input name="password" type="password"></p>
        <button type="submit">Login>></button></p>
      </div>
    </form>
  </s:else>
</div>
