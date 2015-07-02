<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/1/15
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
  <s:set name="menuSelected" value="'issues'"/>
</head>
<section class="issues">
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
    <form action="" method="post" id="issue_query_form">
      <div class="hide-when-print">
        <div class="query-form-content">
          <fieldset id="filters" class="collapsible">
            <legend>Filters</legend>
            <div style="display: block;">
              <div class="quarter">
                <input type="checkbox" checked="checked" id="cb_status_id" value="status_id">
                <label for="cb_status_id">Status</label>
              </div>
              <div class="quarter">
                <select id="operators_status_id" name="op[status_id]">
                  <option value="o" selected="selected">open</option>
                  <option value="=">is</option>
                  <option value="!">is not</option>
                  <option value="c">closed</option>
                  <option value="*">any</option>
                </select>
              </div>
              <div class="quarter">
                <select class="value" id="values_status_id" style="visibility: hidden;">
                  <option value="1">New</option>
                  <option value="2">In Progress</option>
                  <option value="3">Resolved</option>
                  <option value="4">Feedback</option>
                  <option value="5">Closed</option>
                  <option value="6">Rejected</option>
                </select>
              </div>
              <div class="quarter">
                <label for="add_filter_select">Add filter</label>
                <select id="add_filter_select">
                  <option value="status_id" disabled="disabled">Status</option>
                  <option value="tracker_id">Tracker</option>
                  <option value="priority_id">Priority</option>
                  <option value="author_id">Author</option>
                  <option value="assigned_to_id">Assignee</option>
                  <option value="subject">Subject</option>
                  <option value="estimated_hours">Estimated time</option>
                  <option value="done_ratio">% Done</option>
                  <option value="watcher_id">Watcher</option><optgroup label="Assignee"><option value="member_of_group">Assignee's group</option>
                  <option value="assigned_to_role">Assignee's role</option></optgroup><optgroup label="Date"><option value="created_on">Created</option>
                  <option value="updated_on">Updated</option>
                  <option value="closed_on">Closed</option>
                  <option value="start_date">Start date</option>
                  <option value="due_date">Due date</option></optgroup><optgroup label="Related issues"><option value="relates">Related to</option>
                  <option value="duplicates">Duplicates</option>
                  <option value="duplicated">Duplicated by</option>
                  <option value="blocks">Blocks</option>
                  <option value="blocked">Blocked by</option>
                  <option value="precedes">Precedes</option>
                  <option value="follows">Follows</option>
                  <option value="copied_to">Copied to</option>
                  <option value="copied_from">Copied from</option></optgroup>
                </select>
              </div>
            </div>
          </fieldset>
          <fieldset id="options" class="collapsed">
            <legend>Options</legend>
            <div style="display: none"></div>
          </fieldset>
        </div>
        <p class="buttons">
          <a class="icon icon-checked">Apply</a>
          <a class="icon icon-reload">Clear</a>
          <a class="icon icon-save">Save</a>
        </p>
      </div>
    </form>
    <form>
      <table id="issues-table" class="list">
        <thead>
        <th><a><img src="/static/images/toggle_check.png"></a></th>
        <th><a>#</a></th>
        <th><a>Tracker</a></th>
        <th><a>Status</a></th>
        <th><a>Priority</a></th>
        <th><a>Subject</a></th>
        <th><a>Assignee</a></th>
        <th><a>Updated</a></th>
        </thead>
        <tbody>
          <!-- Data will be inserted here automatically. -->
        </tbody>
      </table>
    </form>
  </div>
</section>
<script src="/static/javascripts/issues.js"></script>
<script>
  $(document).ready(function() {
    issues.getData(false);
  })
</script>