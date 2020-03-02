<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
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
    <title><fmt:message key="login.title"/></title>
</head>
<body>
<c:set var="pagePath" value="/jsp/login.jsp" scope="session"/>
<%@ include file="/jsp/static/header.jsp" %>
<div class=" container-fluid wrapper">
    <div class="container pt-3">
        <form name="loginForm" method="POST" action=${pageContext.request.contextPath}/controller>
            <div class="form-group">
                <label for="mylogin"><fmt:message key="label.login"/></label>
                <input type="hidden" name="command" value="login">
                <input type="email" class="form-control" id="mylogin" aria-describedby="emailHelp" name="login" value=""
                       required maxlength="45" placeholder="<fmt:message key="label.placeholder"/>">
            </div>
            <div class="form-group">
                <label for="myPassword1"><fmt:message key="label.password"/> </label>
                <input type="password" class="form-control" id="myPassword1" name="password" value="" required
                       maxlength="45" placeholder="<fmt:message key="label.password" />"/>
                <br/>
                ${errorLoginPasswordMessage}
                <br/>
                ${wrongAction}
                <br/>
                ${nullPage}
                <br/>
                ${error}
                <br/>
            </div>
            <input type="submit" class="btn btn-primary" value="<fmt:message key="label.submit"/>"/>
            <a href="${pageContext.request.contextPath}/jsp/signup.jsp" class="btn btn-primary"><fmt:message
                    key="label.signup"/></a>
            <a href="${pageContext.request.contextPath}/jsp/changepassword.jsp" class="btn btn-primary"><fmt:message
                    key="label.changepassword"/></a>
        </form>
    </div>
</div>
<%@ include file="/jsp/static/footer.jsp" %>
</body>
</html>