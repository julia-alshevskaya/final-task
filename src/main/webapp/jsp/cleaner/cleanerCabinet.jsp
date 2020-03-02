<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/my-styles.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <title><fmt:message key="cleaner.title"/></title>
    </head>
<body>
<c:set var="pagePath" value="/jsp/cleaner/cleanerCabinet.jsp" scope="session"/>
<%@ include file="/jsp/static/header.jsp" %>
<div class=" container-fluid wrapper">

    <div class="container pt-5"><br>
        <h3 class="cleaner-greeting mb-5"><fmt:message key="staff.greeting"/></h3>
        <div class="row">
            <div class="col cleaner">
                <a href="${pageContext.request.contextPath}/jsp/cleaner/updateStaffPersonalData.jsp"
                   class="btn btn-info mb-2 mt-5 cleaner-button"><fmt:message
                        key="updateData.updateClientData"/></a>
                <br/>
                <a href="${pageContext.request.contextPath}/jsp/client/showServices.jsp"
                   class="btn btn-info mb-2 mt-5 cleaner-button"><fmt:message
                        key="admin.showServices"/></a>
                <br/>
                <a href="${pageContext.request.contextPath}/jsp/cleaner/showOrdersByCleanerId.jsp"
                   class="btn btn-info mb-2 mt-5 cleaner-button"><fmt:message key="cleanersOrder.submit"/></a>

            </div>
        </div>
    </div>
    <em><ctg:info-time/></em>
</div>
<%@ include file="/jsp/static/footer.jsp" %>
</body>
</html>
