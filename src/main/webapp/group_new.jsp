<%@ page import="com.unicorn.Utils" %>
<%@ page import="com.unicorn.action.GroupAction" %>
<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/7/15
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
  <s:set name="hideMainMenu" value="true"/>
</head>

<div class="main">
  <div class="content" style="width: 100%">
    <h2>New Group</h2>
    <%
      Object updateFlag = Utils.getSessionAttrAndRemoved(session, GroupAction.GROUP_CREATE_SUCCESS_FLAG);
      if (null != updateFlag) {
        if ((boolean)updateFlag) {
    %>
    <div class="flash notice-succeed">Group <strong>${name}</strong> created.</div>
    <%
    } else {
    %>
    <div class="flash notice-failed">Group create failed.</div>
    <%
        }
      }
    %>
    <form action="/group/new.do" id="new_group" method="post">
      <div class="box tabular">
        <p>
          <label for="group_name">
            Name
            <span class="required">*</span>
          </label>
          <input id="group_name" type="text" size="60" name="name">
        </p>
      </div>
      <input type="submit" name="submitType" value="Create">
      <input type="submit" name="submitType" value="Create and continue">
    </form>
  </div>
  <script>
    $('#new_group').validate({
      rules: {
        name: "required"
      },
      messages: {
        name: "please enter a name"
      }
    });
  </script>
</div>
