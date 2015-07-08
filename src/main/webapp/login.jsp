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
 <div class="content" style="width: 100%">
     <form action="/login.do" method="post">
       <div class="login-form" id="login-form">
         <p><span>Login:</span><input name="username" type="text"></p>
         <p><span>Password:</span><input name="password" type="password"></p>
         <button type="submit">Login>></button>
       </div>
     </form>
 </div>
</div>
