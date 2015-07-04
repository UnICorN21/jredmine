<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/2/15
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
  <s:set name="hideMainMenu" value="true"/>
</head>

<section class="projects">
  <aside>
    <h3>Projects</h3>
    <form action="/project/list.do" method="get">
      <label for="closed">
        <input type="checkbox" name="clozed" id="closed" value="true">
        View closed Projects
      </label>
      <p>
        <input type="submit" value="Apply" class="button-small">
      </p>
    </form>
  </aside>
  <div class="content">

    <div class="contextual">
      <a href="">View all issues</a>&nbsp;|&nbsp;
      <a href="">Overall spent time</a>&nbsp;|&nbsp;
      <a href="">Overall activity</a>
    </div>
    <h2>Projects</h2>
    <div class="projects-index">
      <ul class="projects root">
        <s:iterator value="projectList" var="project">
          <li class="root">
            <div class="root">
              <s:if test="%{#project.userByManager.id == #session.user.id}">
                <s:a action="overview" namespace="/project" class="project my-project">
                  <s:param name="id" value="#project.id"/>
                  ${project.name}
                </s:a>
              </s:if>
              <s:else>
                <s:a action="overview" namespace="/project" class="project">
                  <s:param name="id" value="#project.id"/>
                  ${project.name}
                </s:a>
              </s:else>
            </div>
            <s:if test="%{!#project.projects.empty}">
              <ul class="projects">
                <s:iterator value="#project.projects" var="project">
                  <li class="child">
                    <div class="child">
                      <s:if test="%{#project.userByManager.id == #session.user.id}">
                        <s:a action="overview" namespace="/project" class="project my-project">
                          <s:param name="id" value="#project.id"/>
                          ${project.name}
                        </s:a>
                      </s:if>
                      <s:else>
                        <s:a action="overview" namespace="/project" class="project">
                          <s:param name="id" value="#project.id"/>
                          ${project.name}
                        </s:a>
                      </s:else>
                    </div>
                  </li>
                </s:iterator>
              </ul>
            </s:if>
          </li>
        </s:iterator>
      </ul>
    </div>
    <s:if test="%{null != #session.user.id}">
      <p style="text-align: right;">
        <span class="my-project">My Projects</span>
      </p>
    </s:if>
    <p class="other-formats">
      Also available in:
      <span>
        <a class="atom" rel="nofollow" href="">Atom</a>
      </span>
    </p>
  </div>
</section>
