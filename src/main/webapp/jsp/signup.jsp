<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/my-styles.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    <title><fmt:message key="label.signup"/></title>

</head>
<body>
<c:set var="pagePath" value="/jsp/signup.jsp" scope="session"/>
<%@ include file="/jsp/static/header.jsp" %>
<div class=" container-fluid wrapper">
    <div class="container">
        <form name="signupForm" method="POST" action=${pageContext.request.contextPath}/controller>
            <div class="form-group">
                <fmt:message key="signup.instruction"/>
                <br/>
                <label for="login"><fmt:message key="signup.login"/> * </label>
                <input type="hidden" name="command" value="signup"/>

                <input type="email" class="form-control" id="login" aria-describedby="emailHelp" name="login"
                       value="${map.login}" required maxlength="45" placeholder="<fmt:message key="signup.email"/>"/>

            </div>
            <div class="form-group">
                <label for="password"> <fmt:message key="signup.password"/> *</label>
                <input type="password" class="form-control" id="password" name="password" value="${map.password}"
                       required maxlength="45" minlength="8" placeholder="<fmt:message key="signup.password"/>"/>

            </div>
            <div class="form-group">
                <label for="conPassword"> <fmt:message key="signup.confirmedPassword"/> *</label>
                <input type="password" class="form-control" id="conPassword" name="confirmedPassword"
                       value="${map.confirmedPassword}" required maxlength="45" minlength="8"
                       placeholder="<fmt:message key="signup.confirmedPassword"/>"/>
            </div>

            <div class="form-group">
                <label for="signup"><fmt:message key="signup.name"/> * </label>
                <input type="text" class="form-control" id="signup" name="name" value="${map.name}" required
                       maxlength="45" minlength="1"
                       placeholder="<fmt:message key="signup.name"/>"/>
            </div>

            <div class="form-group">
                <label for="signupSurname"><fmt:message key="signup.surname"/> * </label>
                <input type="text" class="form-control" id="signupSurname" name="surname" value="${map.surname}"
                       required maxlength="45" minlength="1"
                       placeholder="<fmt:message key="signup.surname"/>"/>
            </div>

            <div class="form-group">
                <label for="phoneNumber"> <fmt:message key="signup.phone"/> * </label>
                <input type="text" class="form-control" id="phoneNumber" name="phone" value="${map.phone}" required
                       maxlength="45" minlength="3"
                       placeholder="<fmt:message key="signup.number"/>"/>
            </div>

            <div class="form-group">
                <label for="userAddress"> <fmt:message key="signup.address"/> *</label>
                <input type="text" class="form-control" id="userAddress" name="address" value="${map.address}" required
                       maxlength="45"
                       placeholder="<fmt:message key="signup.address"/>"/>
                <br/>
                ${wrongAction}
                <br/>
                ${nullPage}
                <br/>
                ${error}
                <br/>
                ${signupError}
                <input type="submit" class="btn btn-primary" value="<fmt:message key="signup.submit"/>"/>
        </form>
        <br>
    </div>
</div>
<%--<%@ include file="/jsp/static/footer.jsp" %>--%>
</body>
</html>
