<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value= "${locale}" />
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/my-styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title><fmt:message key="deleteService.title"/></title>
</head>
<body>
<c:set var="pagePath" value="/jsp/admin/deleteService.jsp" scope="session" />
<%@ include file="/jsp/static/header.jsp"%>
<div class=" container-fluid wrapper">
<div class="container pt-5">
    <form name="addServiceForm" method="POST" action=${pageContext.request.contextPath}/controller>
        <input type="hidden" name="command" value="delete_service"/>
        <%--        <fmt:message key="addOrder.instruction"/>--%>

        <div class="form-group">
            <label for="delService"><fmt:message key="deleteService.id"/></label>
            <input type="text" id="delService" class="form-control" name="serviceId" value="" required  maxlength="45"  placeholder="<fmt:message key="deleteService.id"/>"/>
        </div>

<%--        <br/> <fmt:message key="deleteService.id"/> <br/>--%>
<%--        <input type="text" name="serviceId" value="" required  maxlength="45"  placeholder="<fmt:message key="deleteService.id"/>"/>--%>
<%--        <br/>--%>
        <%--        <br/>--%>
        <%--        ${serviceAdded}--%>
        <br/>
        ${errorData}
        <br/>
        <input type="submit" class="btn btn-primary" value="<fmt:message key="deleteOrder.submit"/>"/>
        <a href="${pageContext.request.contextPath}/jsp/admin/admin.jsp" class="btn btn-primary"><fmt:message key="admin.home"/></a>
    </form>
    <br>
</div>
</div>
<%@ include file="/jsp/static/footer.jsp" %>
</body>
</html>
