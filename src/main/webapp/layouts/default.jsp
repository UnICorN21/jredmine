<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/1/15
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="/static/stylesheets/normalize.css"/>
  <link rel="stylesheet/less" type="text/css" href="/static/stylesheets/main.less"/>
  <script src="/static/javascripts/less.min.js"></script>
  <script src="/static/javascripts/jquery-1.11.3.min.js"></script>
  <title>JRedmine</title>
</head>
<body>
<decorator:head/>
<jsp:include page="../includes/header.jsp"/>
<div class="main">
  <decorator:body/>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
