<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ru_RU"/>
<%--<fmt:setLocale value="${locale}"/>--%>
<fmt:setBundle basename="messages" />
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
    <title><fmt:message key="label.signup"/></title>

</head>
<body>
<%@ include file="/jsp/static/header.jsp"%>
<div class=" container-fluid wrapper">
<div class="container">

    <form name="signupStaffForm" method="POST" action=${pageContext.request.contextPath}/controller>
        <input type="hidden" name="command" value="signup_staff"/>
        <p class="order__paragraph pt-2"><fmt:message key="signup.instruction"/></p>
        <div class="form-group">
            <label for="signLogin"><fmt:message key="signup.login"/> * </label>
            <input type="email" id="signLogin" class="form-control" name="login" value="${map.login}" required  maxlength="45"  placeholder="<fmt:message key="signup.email"/>"/>
        </div>
        <div class="form-group">
            <label for="signPassword"><fmt:message key="signup.password"/> *</label>
            <input type="password" id="signPassword" class="form-control" name="password" value="${map.password}" required  maxlength="45" minlength="8" placeholder="<fmt:message key="signup.password"/>"/>
        </div>
        <div class="form-group">
            <label for="confPassword"><fmt:message key="signup.confirmedPassword"/> *</label>
            <input type="password" id="confPassword" class="form-control" name="confirmedPassword" value="${map.confirmedPassword}" required  maxlength="45" minlength="8" placeholder="<fmt:message key="signup.confirmedPassword"/>"/>
        </div>
        <div class="form-group">
            <label for="signName"><fmt:message key="signup.name"/> *</label>
            <input type="text" id="signName" class="form-control" name="name" value="${map.name}" required  maxlength="45" minlength="0"
                   placeholder="<fmt:message key="signup.name"/>" />
        </div>
        <div class="form-group">
            <label for="signSurname"><fmt:message key="signup.surname"/> *</label>
            <input type="text" id="signSurname" class="form-control" name="surname" value="${map.surname}" required  maxlength="45" minlength="0"
                   placeholder="<fmt:message key="signup.surname"/>" />
        </div>
        <div class="form-group">
            <label for="signPhone"><fmt:message key="signup.phone"/> *</label>
            <input type="text" id="signPhone" class="form-control" name="phone" value="${map.phone}" required  maxlength="45" minlength="0"
                   placeholder="<fmt:message key="signup.number"/>"/>
        </div>
        <div class="form-group">
            <label for="signAddress"><fmt:message key="signup.address"/> *</label>
            <input type="text" id="signAddress" class="form-control" name="address" value="${map.address}" required  maxlength="45"
                   placeholder="<fmt:message key="signup.address"/>"/>
        </div>
        <div class="form-group">
            <label for="cleanerRol"><fmt:message key="signupcleaner.role"/> *</label>
            <input type="text" id="cleanerRol" class="form-control" name="role" value="" required  maxlength="45"
                   placeholder="administrator/cleaner"/>
        </div>




<%--        <br/> <fmt:message key="signup.login"/> * <br/>--%>
<%--        <input type="email" name="login" value="${map.login}" required  maxlength="45"  placeholder="<fmt:message key="signup.email"/>"/>--%>
<%--        <br/><fmt:message key="signup.password"/> *<br/>--%>
<%--        <input type="password" name="password" value="${map.password}" required  maxlength="45" minlength="8" placeholder="<fmt:message key="signup.password"/>"/>--%>
<%--        <br/><fmt:message key="signup.confirmedPassword"/> *<br/>--%>
<%--        <input type="password" name="confirmedPassword" value="${map.confirmedPassword}" required  maxlength="45" minlength="8" placeholder="<fmt:message key="signup.confirmedPassword"/>"/>--%>
<%--        <br/>--%>
<%--        <br/><fmt:message key="signup.name"/> *<br/>--%>
<%--        <input type="text" name="name" value="${map.name}" required  maxlength="45" minlength="0"--%>
<%--               placeholder="<fmt:message key="signup.name"/>" />--%>
<%--        <br/><fmt:message key="signup.surname"/> * <br/>--%>
<%--        <input type="text" name="surname" value="${map.surname}" required  maxlength="45" minlength="0"--%>
<%--               placeholder="<fmt:message key="signup.surname"/>" />--%>
<%--        <br/><fmt:message key="signup.phone"/> *<br/>--%>
<%--        <input type="text" name="phone" value="${map.phone}" required  maxlength="45" minlength="0"--%>
<%--               placeholder="<fmt:message key="signup.number"/>"/>--%>
<%--        <br/><fmt:message key="signup.address"/> *<br/>--%>
<%--        <input type="text" name="address" value="${map.address}" required  maxlength="45"--%>
<%--               placeholder="<fmt:message key="signup.address"/>"/>--%>
<%--        <br/><fmt:message key="signupcleaner.role"/> *<br/>--%>
<%--        <input type="text" name="role" value="" required  maxlength="45"--%>
<%--               placeholder="administrator/cleaner"/>--%>
        <br/>
        ${employeeAdded}
        ${wrongAction}
        ${nullPage}
        ${error}
        ${signupCleanerError}
        <br/>
        <br/>
        <input type="submit" class="btn btn-primary" value="<fmt:message key="signup.staffSubmit"/>"/>
        <a href="${pageContext.request.contextPath}/jsp/admin/admin.jsp" class="btn btn-primary"><fmt:message key="show.home"/></a>
    </form>
    <br>
</div>
</div>
<%@ include file="/jsp/static/footer.jsp" %>
</body>
</html>
