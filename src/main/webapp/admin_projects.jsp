<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/6/15
  Time: 5:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<head>
  <s:set name="hideMainMenu" value="true"/>
</head>

<div class="main">
  <div class="content">
    <div class="contextual">
      <a class="icon icon-add" href="/project_new.jsp">New Project</a>
    </div>
    <h2>Projects</h2>
    <form action="" method="get">
      <fieldset>
        <legend>Filters</legend>
        <label for="status">Status:</label>
        <select id="status">
          <option>all</option>
        </select>
        <label for="name">Project:</label>
        <input id="name" size="30" type="text" name="name">
        <input type="submit" value="Apply" class="small">
        <s:a action="projects" namespace="admin" class="icon icon-reload">Clear</s:a>
      </fieldset>
    </form>
    <div class="autoscroll">
      <table class="list">
        <thead>
          <tr>
            <th>Project</th>
            <th>Public</th>
            <th>Created</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
        <s:iterator value="projects" var="project" status="p">
          <s:if test="%{true == #p.odd}">
          <tr class="root odd">
          </s:if>
          <s:else>
          <tr class="root even">
          </s:else>
            <td class="name"><span>
              <s:a action="settings" namespace="/project">
                <s:param name="id" value="#project.id"/>
                ${project.name}
              </s:a>
            </span></td>
            <td>
              <s:if test="%{#project.isPublic}">
                <img src="/static/images/toggle_check.png" alt="Toggle check">
              </s:if>
            </td>
            <td>${project.createTime}</td>
            <td class="buttons">
              <a class="icon icon-lock" href="">Archive</a>
              <s:a class="icon icon-copy" action="copy" namespace="/project">
                <s:param name="id" value="#project.id"/>
                Copy
              </s:a>
              <s:a class="icon icon-copy" action="delete" namespace="/project">
                <s:param name="id" value="#project.id"/>
                Delete
              </s:a>
            </td>
          </tr>
          <s:iterator value="#project.projects" var="sub" status="pp">
            <s:if test="%{true == (#p.odd ^ #pp.odd)}">
            <tr class="child odd">
            </s:if>
            <s:else>
            <tr class="child even">
            </s:else>
            <td class="name"><span>
              <s:a action="settings" namespace="/project">
                <s:param name="id" value="#sub.id"/>
                ${sub.name}
              </s:a>
            </span></td>
            <td><img src="/static/images/toggle_check.png" alt="Toggle check"></td>
            <td>${sub.createTime}</td>
            <td class="buttons">
              <a class="icon icon-lock" href="">Archive</a>
              <s:a class="icon icon-copy" action="copy" namespace="/project">
                <s:param name="id" value="#project.id"/>
                Copy
              </s:a>
              <s:a class="icon icon-copy" action="delete" namespace="/project">
                <s:param name="id" value="#sub.id"/>
                Delete
              </s:a>
            </td>
            </tr>
          </s:iterator>
        </s:iterator>
        </tbody>
      </table>
    </div>
  </div>
</div>
