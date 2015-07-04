<%@ page import="com.opensymphony.xwork2.util.ValueStack" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.unicorn.Utils" %>
<%@ page import="com.unicorn.action.IssueAction" %>
<%@ page import="com.unicorn.domain.History" %>
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
    <%
      Object updateFlag = Utils.getSessionAttrAndRemoved(session, IssueAction.ISSUE_UPDATE_SUCCESS_FLAG);
      if (null != updateFlag) {
        if ((boolean)updateFlag) {
    %>
    <div class="flash notice-succeed">Successful updated.</div>
    <%
        } else {
    %>
    <div class="flash notice-failed">Update failed.</div>
    <%
        }
      }
    %>
    <s:if test="%{null != #session.user.id}">
      <div class="contextual">
        <a class="icon icon-edit" onclick="util.showAndScrollTo('update'); return false;" href="">Edit</a>
        <a class="icon icon-time-add" onclick="util.showAndScrollTo('update'); return false;" href="">Log time</a>
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
          <td class="status">${status.desc}</td>
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
      <s:iterator value="%{histories}" var="history" status="h">
        <div id="change-${history.id}" class="journal">
          <div id="note-<s:property value="#h.index+1"/>">
            <h4>
              <a class="journal-link" href="#note-<s:property value="#h.index+1"/>">#<s:property value="#h.index+1"/></a>
              Updated by
              <a class="user" href="">${history.author.username}</a>
              <%
                Timestamp historyLogTime = ((History)vs.findValue("history")).getLogTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String historyTitle = df.format(historyLogTime);
                long historytime = historyLogTime.getTime();
                String historyAgo = Utils.time2ago(historytime);
              %>
              <a title="${history.logTime}" href=""><%=historyAgo%></a>&nbsp;ago
            </h4>
          </div>
          <ul class="detail">
            <s:iterator value="#history.logs" var="logItem">
              <li>
                <strong>${logItem.editProperty}</strong>
                &nbsp;changed from&nbsp;${logItem.oldValue}&nbsp;to&nbsp;${logItem.newValue}
              </li>
            </s:iterator>
          </ul>
        </div>
      </s:iterator>
    </div>
    <s:if test="%{null != #session.user.id}">
      <div id="update" style="display: none">
        <h3>Edit</h3>
        <form id="issue-form" class="edit_issue" enctype="multipart/form-data" action="/edit_issue.do" method="post">
          <input type="hidden" name="editedIssue.id" value="${id}"/>
          <div class="box">
            <s:set name="doneRatio" value="{0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100}"/>
            <fieldset class="tabular">
              <legend>Change properties</legend>
              <div class="all_attributes">
                <p>
                  <label for="issue_project_id">
                    Project
                    <span class="required">*</span>
                  </label>
                  <select id="issue_project_id" name="editedIssue.projectId" required>
                    <option value="${project.id}">${project.name}</option>
                  </select>
                </p>
                <p>
                  <label for="issue_tracker_id">
                    Tracker
                    <span class="required">*</span>
                  </label>
                  <s:select id="issue_tracker_id" name="editedIssue.tracker" value="tracker"
                          list="@com.unicorn.domain.Issue$Tracker@values()" requiredLabel="true"
                          listKey="name()" listValue="desc"/>
                </p>
                <p>
                  <label for="issue_subject">
                    Subject
                    <span class="required">*</span>
                  </label>
                  <input type="text" size="80" maxlength="255" value="${subject}"
                         required name="editedIssue.subject" id="issue_subject">
                </p>
                <p>
                  <label for="issue_description">Description</label>
                  <a href="#" class="icon icon-edit"></a>
                  <span id="issue_description" style="display: none"></span>
                </p>
                <div id="attributes" class="attributes">
                  <div class="split-content">
                    <div class="split-content-left">
                      <p>
                        <label for="issue_status_id">
                          Status
                          <span class="required">*</span>
                        </label>
                        <s:select id="issue_status_id" name="editedIssue.status" value="status"
                                list="@com.unicorn.domain.Issue$Status@values()" requiredLabel="true"
                                listKey="name()" listValue="desc"/>
                      </p>
                      <p>
                        <label for="issue_priority_id">
                          Priority
                          <span class="required">*</span>
                        </label>
                        <s:select id="issue_priority_id" name="editedIssue.priority" value="priority"
                                  list="@com.unicorn.domain.Issue$Priority@values()" requiredLabel="true"
                                  listKey="name()" listValue="desc"/>
                      </p>
                      <p>
                        <%--<label for="issue_assignee_id">Assignee</label>--%>
                        <%--<s:select id="issue_assignee_id" name="editedIssue.assigneeId" value="#session.user.id"--%>
                                <%--list="#developers" listKey="id" listValue="username"/>--%>
                      </p>
                    </div>
                    <div class="split-content-right">
                      <p>
                        <label for="issue_parent_issue_id">Parent task</label>
                        <input id="issue_parent_issue_id" type="text" size="10"
                               name="editedIssue.parentId" autocomplete="off" value="${parent.id}">
                      </p>
                      <p>
                        <label for="issue_start_date">Start date</label>
                        <input id="issue_start_date" size="10" type="date" name="editedIssue.startDate" value="${startDate}">
                      </p>
                      <p>
                        <label for="issue_due_date">Due date</label>
                        <input id="issue_due_date" size="10" type="date" name="editedIssue.dueDate" value="${dueDate}">
                      </p>
                      <p>
                        <label for="issue_estimated_time">Estimated time</label>
                        <input id="issue_estimated_time" size="3" type="number"
                               name="editedIssue.estimatedTime" value="${estimatedTime}">&nbsp;Hours
                      </p>
                      <p>
                        <label for="issue_done_radio">%&nbsp;Done</label>
                        <input id="issue_done_radio" name="editedIssue.progress" type="range"
                               min="0" max="100" value="${progress}">&nbsp;
                        <span id="issue_show_done_radio">${progress}</span>%
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </fieldset>
            <fieldset class="tabular">
              <legend>Log time</legend>
              <div class="split-content-left">
                <p>
                  <label for="time_entry_hours">Spent time</label>
                  <input id="time_entry_hours" size="6" type="number" name="loggedTime.logHours">&nbsp;Hours
                </p>
              </div>
              <div class="split-content-right">
                <p>
                  <label for="time_entry_activity_id">Activity</label>
                  <select id="time_entry_activity_id" name="loggedTime.activityId">
                    <!-- TODO: check and design for the `activity` module -->
                    <option>--- Please select ---</option>
                  </select>
                </p>
              </div>
              <p>
                <label for="time_entry_comments">Comment</label>
                <input id="time_entry_comments" type="text" size="60" name="loggedTime.comments">
              </p>
            </fieldset>
            <fieldset class="tabular">
              <legend>Notes</legend>
              <p>
                <label for="issue_notes">Notes</label>
                <input id="issue_notes" type="text" name="_unsolved_notes">
              </p>
            </fieldset>
          </div>
          <input type="submit" name="commit" value="submit" disabled/>
        </form>
      </div>
    </s:if>
  </div>
  <script src="/static/javascripts/singleissue.js"></script>
</section>


