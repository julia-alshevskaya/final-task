<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${locale}"/>
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
    <title><fmt:message key="guest.title"/></title>
</head>
<body>
<%--<ctg:info-time/>--%>
<c:set var="pagePath" value="/jsp/guest.jsp" scope="session"/>
<%@ include file="/jsp/static/headerGuest.jsp" %>
<div class=" container-fluid wrapper">
    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link my-link" href="#serv"><fmt:message key="guest.services"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link my-link" href="#contact"><fmt:message key="guest.contacts"/></a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
    <section class="advantages">
        <div class="container mb-5">
            <div class="row pt-5 pb-3">
                <div class="col">
                    <h1><fmt:message key="guest.info1"/></h1>
                    <p><fmt:message key="guest.info2"/></p>
                    <ul>
                        <li><fmt:message key="guest.info3"/></li>
                        <li><fmt:message key="guest.info4"/></li>
                        <li><fmt:message key="guest.info5"/></li>
                        <li><fmt:message key="guest.info6"/></li>
                        <li><fmt:message key="guest.info7"/></li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <section class="features">
        <div class="container border pt-3 pb-3">
            <h2><fmt:message key="guest.mainTitle"/><a name="serv"></a></h2>
            <div class="row pt-5 pb-3">
                <div class="col">
                    <div class="card" style="width: 18rem;">
                        <img src="images/window.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><fmt:message key="guest.firstBox.label"/></h5>
                            <p class="card-text"><fmt:message key="guest.firstBox.info"/></p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card" style="width: 18rem;">
                        <img src="images/remont.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><fmt:message key="guest.secondBox.label"/></h5>
                            <p class="card-text"><fmt:message key="guest.secondBox.info"/></p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card" style="width: 18rem;">
                        <img src="images/cottage.jpg" class="card-img-top" alt="...">
                        <div class="card-body ">
                            <h5 class="card-title"><fmt:message key="guest.thirdBox.label"/></h5>
                            <p class="card-text"><fmt:message key="guest.thirdBox.info"/></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row ">
                <div class="col">
                    <div class="card" style="width: 18rem;">
                        <img src="images/office.jpg" class="card-img-top" alt="...">
                        <div class="card-body ">
                            <h5 class="card-title"><fmt:message key="guest.fourthBox.label"/></h5>
                            <p class="card-text"><fmt:message key="guest.fourthBox.info"/></p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card" style="width: 18rem;">
                        <img src="images/pozhar.jpg" class="card-img-top" alt="...">
                        <div class="card-body ">
                            <h5 class="card-title"><fmt:message key="guest.fifthBox.label"/></h5>
                            <p class="card-text"><fmt:message key="guest.fifthBox.info"/></p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card" style="width: 18rem;">
                        <img src="images/eko.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><fmt:message key="guest.sixthBox.label"/></h5>
                            <p class="card-text "><fmt:message key="guest.sixthBox.info"/></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<%@ include file="/jsp/static/footer.jsp" %>
</body>
</html>
