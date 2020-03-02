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
    <title><fmt:message key="updateOrder.title"/></title>
</head>
<body>
<c:set var="pagePath" value="/jsp/admin/updateOrder.jsp" scope="session" />
<%@ include file="/jsp/static/header.jsp"%>
<div class=" container-fluid wrapper">
<div class="container">
    <form name="updateServiceForm" method="POST" action=${pageContext.request.contextPath}/controller>
        <input type="hidden" name="command" value="update_order"/>
        <p class="order__paragraph pt-2"><fmt:message key="updateOrder.instruction"/></p>
        <div class="form-group">
            <label for="orderUpdate"><fmt:message key="updateOrder.id"/></label>
            <input type="text" id="orderUpdate" class="form-control" name="orderId" value="" required  maxlength="45"  placeholder="<fmt:message key="updateOrder.id"/>"/>
        </div>
        <div class="form-group">
            <label for="updateDate"><fmt:message key="updateOrder.date"/></label>
            <input type="text" id="updateDate" class="form-control" name="date" value="" required  maxlength="45"  placeholder="<fmt:message key="updateOrder.date"/>"/>
        </div>
        <div class="form-group">
            <label for="updatePrice"><fmt:message key="updateOrder.price"/> </label>
            <input type="text" id="updatePrice" class="form-control" name="price" value="" required  maxlength="45" minlength="0"
                   placeholder="<fmt:message key="updateOrder.price"/>" />
        </div>



<%--        <br/> <fmt:message key="updateOrder.id"/> <br/>--%>
<%--        <input type="text" name="orderId" value="" required  maxlength="45"  placeholder="<fmt:message key="updateOrder.id"/>"/>--%>
<%--        <br/> <fmt:message key="updateOrder.date"/> <br/>--%>
<%--        <input type="text" name="date" value="" required  maxlength="45"  placeholder="<fmt:message key="updateOrder.date"/>"/>--%>
<%--        <br/><fmt:message key="updateOrder.price"/> <br/>--%>
<%--        <input type="text" name="price" value="" required  maxlength="45" minlength="0"--%>
<%--               placeholder="<fmt:message key="updateOrder.price"/>" />--%>
<%--        <br/><fmt:message key="updateOrder.clientId"/>  <br/>--%>
<%--        <input type="text" name="clientId" value="" required  maxlength="45" minlength="0"--%>
<%--               placeholder="<fmt:message key="updateOrder.clientId"/>" />--%>
<%--        <br/>--%>
<%--        ${serviceUpdated}--%>
        <br/>
        ${errorData}
        <br/>
        <input type="submit" class="btn btn-primary" value="<fmt:message key="updateOrder.submit"/>"/>
        <a href="${pageContext.request.contextPath}/jsp/admin/admin.jsp" class="btn btn-primary"><fmt:message key="show.home"/></a>
    </form>
    <br>
</div>
</div>
<%@ include file="/jsp/static/footer.jsp" %>
</body>
</html>

