<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
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
    <title><fmt:message key="admin.title"/></title>
</head>
<body>

<c:set var="pagePath" value="/jsp/admin/admin.jsp" scope="session"/>
<%@ include file="/jsp/static/header.jsp" %>
<div class=" container-fluid wrapper">
<div class="container-fluid pt-5 mb-5 ">
    <h3><fmt:message key="staff.greeting"/></h3>
</div>
<div class="container-fluid mt-3">
    <div class="row">
        <div class="col-8 border">
            <div class="Admin-form">
                <form method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="show_clients">
                    <input type="submit" class="btn btn-info admin-button" value="<fmt:message key="admin.showClients"/>"/>
                    <a href="${pageContext.request.contextPath}/jsp/admin/admin.jsp"
                       class="btn btn-info"><fmt:message key="admin.home"/></a>
                </form>
                <c:choose>
                    <c:when test="${not empty clientList}">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <td><fmt:message key="admin.clientListColumnOne"/></td>
                                <td><fmt:message key="admin.clientListColumnTwo"/></td>
                                <td><fmt:message key="admin.clientListColumnThree"/></td>
                                <td><fmt:message key="admin.clientListColumnFour"/></td>
                                <td><fmt:message key="admin.clientListColumnFive"/></td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="client" items="${clientList}">
                                <c:set var="classSuccess" value=""/>
                                <tr class="${classSuccess}">
                                    <td>${client.clientId}</td>
                                    <td>${client.name}</td>
                                    <td>${client.surname}</td>
                                    <td>${client.address}</td>
                                    <td>${client.phone}</td>
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
            <div class="Admin-form">
                <form method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="show_staff">
                    <input type="submit" class="admin-button btn btn-info" value="<fmt:message key="admin.showStaff"/>"/>
                </form>
                <c:choose>
                    <c:when test="${not empty staffList}">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <td><fmt:message key="admin.staffListColumnOne"/></td>
                                <td><fmt:message key="admin.staffListColumnTwo"/></td>
                                <td><fmt:message key="admin.staffListColumnThree"/></td>
                                <td><fmt:message key="admin.staffListColumnFour"/></td>
                                <td><fmt:message key="admin.staffListColumnFive"/></td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="cleaner" items="${staffList}">
                                <c:set var="classSuccess" value=""/>
                                <tr class="${classSuccess}">
                                    <td>${cleaner.cleanerId}</td>
                                    <td>${cleaner.name}</td>
                                    <td>${cleaner.surname}</td>
                                    <td>${cleaner.address}</td>
                                    <td>${cleaner.phone}</td>
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
            <div class="Admin-form">
                <form method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="show_orders">
                    <input type="submit" class="btn btn-info admin-button" value="<fmt:message key="admin.showOrders"/>"/>
                </form>
                <c:choose>
                    <c:when test="${not empty orderList}">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <td><fmt:message key="admin.ListOrdersColumnOne"/></td>
                                <td><fmt:message key="admin.ListOrdersColumnTwo"/></td>
                                <td><fmt:message key="admin.ListOrdersColumnThree"/></td>
                                <td><fmt:message key="admin.ListOrdersColumnFour"/></td>
                                <td><fmt:message key="admin.ListOrdersColumnFive"/></td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="order" items="${orderList}">
                                <c:set var="classSuccess" value=""/>
                                <tr class="${classSuccess}">
                                    <td>${order.orderId}</td>
                                    <td>${order.dateOrder}</td>
                                    <td>${order.price}</td>
                                    <td>${order.clientId}</td>
                                    <td>${order.cleanerId}</td>
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
        <div class="col-4 border pr-1">

            <a class="admin-button btn btn-light  mt-2 mb-2" href="${pageContext.request.contextPath}/jsp/admin/signupstaff.jsp"><fmt:message
                    key="admin.signupEmployee"/></a>
            <br/>
            <a class="admin-button btn btn-light mb-2" href="${pageContext.request.contextPath}/jsp/admin/addService.jsp"><fmt:message
                    key="admin.addService"/></a>
            <br/>
            <a class="admin-button btn btn-light mb-2 " href="${pageContext.request.contextPath}/jsp/admin/addOrder.jsp"><fmt:message key="addOrder.submit"/></a>
            <br/>
            <a class="admin-button btn btn-light mb-2" href="${pageContext.request.contextPath}/jsp/admin/updateService.jsp"><fmt:message
                    key="admin.updateService"/></a>
            <br/>
            <a class="admin-button btn btn-light mb-2" href="${pageContext.request.contextPath}/jsp/admin/updateOrder.jsp"><fmt:message
                    key="updateOrder.updateOrder"/></a>
            <br/>
            <a class="admin-button btn btn-light mb-2" href="${pageContext.request.contextPath}/jsp/client/showServices.jsp"><fmt:message
                    key="admin.showServices"/></a>
            <br/>
            <a class="admin-button btn btn-light mb-2" href="${pageContext.request.contextPath}/jsp/admin/deleteOrder.jsp"><fmt:message
                    key="deleteOrder.submit"/></a>
            <br/>
            <a class="admin-button btn btn-light mb-2 " href="${pageContext.request.contextPath}/jsp/admin/deleteService.jsp"><fmt:message
                    key="deleteService.submit"/></a>
        </div>
    </div>
</div>
    <em><ctg:info-time/></em>
</div>
<%@ include file="/jsp/static/footer.jsp" %>
</body>
</html>
