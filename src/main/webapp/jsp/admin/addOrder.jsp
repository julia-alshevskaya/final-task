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
    <title><fmt:message key="addOrder.title"/></title>
</head>
<body>
<c:set var="pagePath" value="/jsp/admin/addOrder.jsp" scope="session" />
<%@ include file="/jsp/static/header.jsp"%>
<div class=" container-fluid wrapper">
<div class="container">
    <form name="addServiceForm" method="POST" action=${pageContext.request.contextPath}/controller>
        <input type="hidden" name="command" value="add_order"/>
        <p class="order__paragraph pt-2"><fmt:message key="addOrder.instruction"/></p>
        <div class="form-group">
            <label for="orderDate"><fmt:message key="addOrder.date"/></label>
            <input class="form-control" id="orderDate"type="text" name="date" value="" required  maxlength="45"  placeholder="<fmt:message key="addOrder.date"/>"/>
        </div>

        <div class="form-group">
            <label for="orderPrice"><fmt:message key="addOrder.price"/></label>
            <input class="form-control" id="orderPrice" type="text" name="price" value="" required  maxlength="45" minlength="0" placeholder="<fmt:message key="addOrder.price"/>" />
        </div>

        <div class="form-group">
            <label for="idClient"><fmt:message key="addOrder.idClient"/></label>
            <input class="form-control" id="idClient" type="text" name="clientId" value="" required  maxlength="45" minlength="0" placeholder="<fmt:message key="addOrder.idClient"/>" />
        </div>

        <div class="form-group">
            <label for="cleanerId"><fmt:message key="addOrder.cleanerId"/></label>
            <input class="form-control" id="cleanerId" type="text" name="cleanerId" value="" required  maxlength="45" minlength="0"
                   placeholder="<fmt:message key="addOrder.cleanerId"/>" />
        </div>
        <br/>
        ${errorData}
        <br/>
        <input type="submit" class="btn btn-primary" value="<fmt:message key="addOrder.submit"/>"/>
        <a href="${pageContext.request.contextPath}/jsp/admin/admin.jsp" class="btn btn-primary"><fmt:message key="admin.home"/></a>
    </form>



<%--        <input type="hidden" name="command" value="add_order"/>--%>
<%--        <fmt:message key="addOrder.instruction"/>--%>
<%--        <br/> <fmt:message key="addOrder.date"/> <br/>--%>
<%--        <input type="text" name="date" value="" required  maxlength="45"  placeholder="<fmt:message key="addOrder.date"/>"/>--%>
<%--        <br/><fmt:message key="addOrder.price"/> <br/>--%>
<%--        <input type="text" name="price" value="" required  maxlength="45" minlength="0"--%>
<%--               placeholder="<fmt:message key="addOrder.price"/>" />--%>
<%--        <br/><fmt:message key="addOrder.idClient"/>  <br/>--%>
<%--        <input type="text" name="clientId" value="" required  maxlength="45" minlength="0"--%>
<%--               placeholder="<fmt:message key="addOrder.idClient"/>" />--%>
<%--        <br/><fmt:message key="addOrder.cleanerId"/>  <br/>--%>
<%--        <input type="text" name="cleanerId" value="" required  maxlength="45" minlength="0"--%>
<%--               placeholder="<fmt:message key="addOrder.cleanerId"/>" />--%>
<%--        <br/>--%>
<%--        ${serviceAdded}--%>
<%--        <br/>--%>
<%--        ${errorData}--%>
<%--        <br/>--%>
<%--        <input type="submit" class="btn btn-primary" value="<fmt:message key="addOrder.submit"/>"/>--%>
<%--        <a href="${pageContext.request.contextPath}/jsp/admin/admin.jsp" class="btn btn-primary"><fmt:message key="admin.home"/></a>--%>
<%--    </form>--%>
<%--    <br>--%>
</div>
</div>
<%@ include file="/jsp/static/footer.jsp" %>
</body>
</html>
