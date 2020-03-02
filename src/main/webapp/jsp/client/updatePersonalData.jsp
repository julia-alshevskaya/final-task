<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
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
<c:set var="pagePath" value="/jsp/client/updatePersonalData.jsp" scope="session"/>
<%@ include file="/jsp/static/header.jsp" %>
<div class=" container-fluid wrapper">
    <div class="container pt-3">
        <form method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="update_personal_data">
            <p class="order__paragraph mt-2"><fmt:message key="updateData.instruction"/></p>
            <div class="form-group">
                <label for="updateNam"><fmt:message key="updateData.updateName"/> </label>
                <input type="text" name="name" class="form-control" id="updateNam" value="" required maxlength="45"
                       placeholder="<fmt:message key="updateData.updateName"/>"/>
            </div>
            <div class="form-group">
                <label for="updateSnam"> <fmt:message key="updateData.surname"/></label>
                <input type="text" name="surname" id="updateSnam" class="form-control" value="" required maxlength="45"
                       placeholder="<fmt:message key="updateData.surname"/>"/>
            </div>
            <div class="form-group">
                <label for="upDataPhone"><fmt:message key="updateData.phone"/></label>
                <input type="text" id="upDataPhone" class="form-control" name="phone" value="" required maxlength="45"
                       minlength="1"
                       placeholder="<fmt:message key="updateData.phone"/>"/>
            </div>
            <div class="form-group">
                <label for="upDataAddress"><fmt:message key="updateData.address"/> </label>
                <input type="text" id="upDataAddress" class="form-control" name="address" value="" required
                       maxlength="45" minlength="1"
                       placeholder="<fmt:message key="updateData.address"/>"/>
            </div>
            <br/>
            ${errorData}
            <br/>
            <input type="submit" class="btn btn-primary" value="<fmt:message key="updateData.submit"/>"/>
            <a href="${pageContext.request.contextPath}/jsp/client/main.jsp" class="btn btn-primary"><fmt:message
                    key="show.home"/></a>
        </form>
        <br/>
        ${updateDataError}
        <br/>
        ${error}
    </div>
</div>
<%@ include file="/jsp/static/footer.jsp" %>
</body>
</html>
