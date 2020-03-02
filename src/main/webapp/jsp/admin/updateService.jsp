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
    <title><fmt:message key="updateService.title"/></title>
</head>
<body>
<c:set var="pagePath" value="/jsp/admin/updateService.jsp" scope="session" />
<%@ include file="/jsp/static/header.jsp"%>
<div class=" container-fluid wrapper">
<div class="container">
    <form name="updateServiceForm" method="POST" action=${pageContext.request.contextPath}/controller>
        <input type="hidden" name="command" value="update_service"/>
        <p class="order__paragraph pt-2"><fmt:message key="service.updateInstruction"/></p>
        <div class="form-group">
            <label for="serviceUpdateId"><fmt:message key="service.updateId"/> </label>
            <input type="text" id="serviceUpdateId" class="form-control" name="serviceId" value="" required  maxlength="45"  placeholder="<fmt:message key="service.updateId"/>"/>
        </div>
        <div class="form-group">
            <label for="sName"> <fmt:message key="service.name"/> </label>
            <input type="text" id="sName" class="form-control" name="serviceName" value="" required  maxlength="45"  placeholder="<fmt:message key="service.name"/>"/>
        </div>
        <div class="form-group">
            <label for="sPrice">  <fmt:message key="service.price"/></label>
            <input type="text" id="sPrice" class="form-control" name="pricePerItem" value="" required  maxlength="45" minlength="0"
                   placeholder="<fmt:message key="service.price"/>" />
        </div>
        <div class="form-group">
            <label for="sQuantity"><fmt:message key="service.quantity"/> </label>
            <input type="text" id="sQuantity" class="form-control" name="quantity" value="" required  maxlength="45" minlength="0"
                   placeholder="<fmt:message key="service.quantity"/>" />

        </div>


<%--        <br/> <fmt:message key="service.updateId"/> <br/>--%>
<%--        <input type="text" name="serviceId" value="" required  maxlength="45"  placeholder="<fmt:message key="service.updateId"/>"/>--%>
<%--        <br/> <fmt:message key="service.name"/> <br/>--%>
<%--        <input type="text" name="serviceName" value="" required  maxlength="45"  placeholder="<fmt:message key="service.name"/>"/>--%>
<%--        <br/><fmt:message key="service.price"/> <br/>--%>
<%--        <input type="text" name="pricePerItem" value="" required  maxlength="45" minlength="0"--%>
<%--               placeholder="<fmt:message key="service.price"/>" />--%>
<%--        <br/><fmt:message key="service.quantity"/>  <br/>--%>
<%--        <input type="text" name="quantity" value="" required  maxlength="45" minlength="0"--%>
<%--               placeholder="<fmt:message key="service.quantity"/>" />--%>
        <br/>
        ${serviceUpdated}
        <br/>
        ${errorData}
        <br/>
        <input type="submit" class="btn btn-primary" value="<fmt:message key="service.updateSubmit"/>"/>
        <a href="${pageContext.request.contextPath}/jsp/admin/admin.jsp" class="btn btn-primary"><fmt:message key="show.home"/></a>

    </form>
    <br>
</div>
</div>
<%@ include file="/jsp/static/footer.jsp" %>
</body>
</html>
