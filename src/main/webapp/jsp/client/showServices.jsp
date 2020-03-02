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
    <title><fmt:message key="show.priceList"/></title>
</head>
<body>
<c:set var="pagePath" value="/jsp/client/showServices.jsp" scope="session"/>
<%@ include file="/jsp/static/header.jsp" %>
<div class=" container-fluid wrapper">
    <div class="container pt-3">
        <form method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="show_services">
            <input type="submit" class="btn btn-primary" value="<fmt:message key="admin.showServices"/>"/>
        </form>
        <c:choose>
            <c:when test="${not empty serviceList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td><fmt:message key="show.columnOne"/></td>
                        <td><fmt:message key="show.columnTwo"/></td>
                        <td><fmt:message key="show.columnThree"/></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="service" items="${serviceList}">
                        <c:set var="classSuccess" value=""/>
                        <tr class="${classSuccess}">
                            <td>${service.serviceId}</td>
                            <td>${service.serviceName}</td>
                            <td>${service.priceItem}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
        </c:choose>
        <br/>
        ${emptyList}
        <br/>
        ${error}
    </div>
</div>
<%@ include file="/jsp/static/footer.jsp" %>
</body>
</html>
