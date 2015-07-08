<%@ page import="com.unicorn.Utils" %>
<%@ page import="com.unicorn.action.TrackerAction" %>
<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/8/15
  Time: 5:11 PM
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
      Object updateFlag = Utils.getSessionAttrAndRemoved(session, TrackerAction.TRACKER_CREATE_SUCCESS_FLAG);
      if (null != updateFlag) {
        if ((boolean)updateFlag) {
    %>
    <div class="flash notice-succeed">Tracker&nbsp;<strong>${name}</strong>&nbsp;created.</div>
    <%
    } else {
    %>
    <div class="flash notice-failed">Tracker create failed.</div>
    <%
        }
      }
    %>
    <form action="/tracker/create.do" id="new_tracker" method="post">
      <div class="box tabular">
        <p>
          <label for="tracker_name">
            Name
            <span class="required">*</span>
          </label>
          <input id="tracker_name" type="text" size="60" name="name">
        </p>
      </div>
      <input type="submit" name="submitType" value="Create">
      <input type="submit" name="submitType" value="Create and continue">
    </form>
  </div>
  <script>
    util.dismiss('div.flash');
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
