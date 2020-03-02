<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
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
    <title><fmt:message key="footer.title"/></title>
</head>
<body>
<footer>
    <div class="container pt-5">
        <div class="row">
            <div class="col-6">
                <p>
                    <fmt:message key="footer.labelOne"/>
                    <br/>
                    support@pachkuli.by
                    <br/>
                    <fmt:message key="footer.labelTwo"/><br/>
                    <fmt:message key="footer.labelThree"/><br/>
                    <a name="contact"></a>
                </p>
            </div>
            <div class="col-6">
                <p>
                    <fmt:message key="footer.labelFour"/><br/>
                    <fmt:message key="footer.labelFive"/><br/>
                    <fmt:message key="footer.labelSix"/><br/>
                    <fmt:message key="footer.labelSeven"/>
                </p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
