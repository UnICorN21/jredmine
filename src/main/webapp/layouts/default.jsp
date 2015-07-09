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
  <jsp:include page="../includes/head.jsp"/>
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
