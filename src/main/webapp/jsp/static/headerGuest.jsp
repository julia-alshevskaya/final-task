<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/my-styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title><fmt:message key="header.header"/></title>
</head>
<body>
<header class="header">
    <div class="container-fluid">
        <div class="row no-gutters ">
            <div class="col-1 mt-1 mb-1 logo">
            </div>
            <div class="col-8 pt-3 ">
                <p><fmt:message key="header.moto"/> <fmt:message key="header.time"/></p>
            </div>
            <div class="col-3 locale pl-3 pt-3">
                <div class="row">
                    <div class="col mt-2">
                        <form method="POST" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="change_locale"/>
                            <select class="selectpicker" name="newLanguage" onchange="submit()">
                                <option><fmt:message key="header.chooseSubmit"/></option>
                                <option value="en_EN">English/Russian</option>
                            </select>
                        </form>
                    </div>
                    <div class="col">
                        <a href="${pageContext.request.contextPath}/jsp/login.jsp"
                           class="btn btn-success ml-2"><fmt:message key="label.submit"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
</body>
</html>