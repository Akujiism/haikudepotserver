<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jwr" uri="http://jawr.net/tags" %>

<%--
This is a single page application and this is essentially the 'single page'.  It boots-up some libraries and other
web-resources and then this starts the java-script single page environment driven by the AngularJS library.
--%>

<html ng-app="haikudepotserver">

<head>

    <title>Haiku Depot Web</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%@include file="/WEB-INF/includes/searchheadlink.jsp"%>
    <%@include file="/WEB-INF/includes/favicons.jsp"%>

    <jwr:script src="/bundles/libs.js"></jwr:script>
    <jwr:script src="/bundles/app.js"></jwr:script>
    <jwr:style src="/bundles/app.css"></jwr:style>

</head>

<body>

<%@include file="/WEB-INF/includes/unsupported.jsp"%>

<banner></banner>

<div class="container">
    <div ng-view></div>
</div>

<%--
This IFRAME can be used by application logic to cause a download to occur.
--%>

<iframe id="download-iframe" style="display:none"></iframe>

</body>

</html>