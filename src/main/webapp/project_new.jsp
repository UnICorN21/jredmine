<%@ page import="java.util.Date" %>
<%@ page import="com.unicorn.action.IssueAction" %>
<%@ page import="com.unicorn.Utils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/1/15
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
  <s:set name="menuSelected" value="'new'"/>
</head>
<section class="new">
  <div class="content" style="width: 100%">
    <%
      Object updateFlag = Utils.getSessionAttrAndRemoved(session, IssueAction.ISSUE_CREATE_SUCCESS_FLAG);
      if (null != updateFlag) {
        if ((boolean)updateFlag) {
    %>
    <div class="flash notice-succeed">Issue #${id} created.</div>
    <%
    } else {
    %>
    <div class="flash notice-failed">Issue create failed.</div>
    <%
        }
      }
    %>
    <h2>New Issue</h2>
    <form id="issue-form" class="new_issue" enctype="multipart/form-data" method="POST" action="/new_issue.do">
      <input type="hidden" name="formIssue.projectId" value="${session.currentProject.id}">
      <div class="box tabular">
        <div id="all_attributes">
          <p>
            <label for="issue_tracker_id">
              Tracker
              <span class="required">*</span>
            </label>
            <s:select id="issue_tracker_id" name="formIssue.tracker" value="@com.unicorn.domain.Issue$Tracker@Feature"
                    list="@com.unicorn.domain.Issue$Tracker@values()" requiredLabel="true"
                    listKey="name()" listValue="desc"/>
          </p>
          <p>
            <label for="issue_subject">
              Subject
              <span class="required">*</span>
            </label>
            <input id="issue_subject" name="formIssue.subject" size="80" maxlength="255" type="text">
          </p>
          <p>
            <label for="issue_description">Description</label>
            <input id="issue_description" name="formIssue.description" size="80" maxlength="255" type="text">
          </p>
          <div id="attributes" class="attributes">
            <div class="split-content">
              <div class="split-content-left">
                <p>
                  <label for="issue_status_id">
                    Status
                    <span class="required">*</span>
                  </label>
                  <s:select id="issue_status_id" name="formIssue.status" value="@com.unicorn.domain.Issue$Status@New"
                            list="@com.unicorn.domain.Issue$Status@values()" requiredLabel="true"
                            listKey="name()" listValue="desc"/>
                </p>
                <p>
                  <label for="issue_priority_id">
                    Priority
                    <span class="required">*</span>
                  </label>
                  <s:select id="issue_priority_id" name="formIssue.priority" value="@com.unicorn.domain.Issue$Priority@Normal"
                            list="@com.unicorn.domain.Issue$Priority@values()" requiredLabel="true"
                            listKey="name()" listValue="desc"/>
                </p>
                <p>
                  <label for="issue_assignee_id">Assignee</label>
                  <s:select id="issue_assignee_id" name="formIssue.assigneeId" headerKey="" headerValue=""
                            list="#session.currentProject.developers" listKey="id" listValue="username"/>
                </p>
              </div>
              <div class="split-content-right">
                <p>
                  <label for="issue_parent_issue_id">Parent task</label>
                  <input id="issue_parent_issue_id" type="text" size="10"
                         name="formIssue.parentId" autocomplete="off">
                </p>
                <p>
                  <label for="issue_start_date">Start date</label>
                  <%
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDate = df.format(new Date());
                  %>
                  <input id="issue_start_date" size="10" type="date" name="formIssue.startDate" value="<%=currentDate%>">
                </p>
                <p>
                  <label for="issue_due_date">Due date</label>
                  <input id="issue_due_date" size="10" type="date" name="formIssue.dueDate" value="${dueDate}">
                </p>
                <p>
                  <label for="issue_estimated_time">Estimated time</label>
                  <input id="issue_estimated_time" size="3" type="number"
                         name="formIssue.estimatedTime">&nbsp;Hours
                </p>
                <p>
                  <label for="issue_done_radio">%&nbsp;Done</label>
                  <input id="issue_done_radio" name="formIssue.progress" type="range"
                         min="0" max="100" value="0">&nbsp;
                  <span id="issue_show_done_radio">0</span>%
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <input type="submit" name="submitType" value="Create" disabled>
      <input type="submit" name="submitType" value="Create and continue" disabled>
    </form>
  </div>
  <script src="/static/javascripts/projectnew.js"></script>
</section>
