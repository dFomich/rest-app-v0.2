<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>
<html lang="${language}">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orders.css">
    <%@ taglib prefix="ctg" uri="customtags" %>
    <title>show_user_orders</title>
    <style type="text/css">
        table {
            font-family: "Lato", Verdana, sans-serif;
            font-size: 17px;
            background: white;
            max-width: 100%;
            width: 100%;
            border-collapse: collapse;
            text-align: left;
            margin-top: 10%;
        }

        th {
            font-weight: normal;
            color: black;
            padding: 10px 15px;
        }

        td {
            color: black;
            border-top: 1px solid black;
            padding: 10px 15px;
        }

        tr:nth-child(2n) {
            background: lightgrey;
        }
    </style>
</head>

<body>
<header class="header">
    <div class="wrapper header__wrapper">
        <div class="header__logo-line">
            <a href="${pageContext.request.contextPath}/jsp/start_page.jsp" class="logo">
                <h1 class="logo__text"><fmt:message key="label.restaurant"/></h1>
            </a>
        </div>
        <div class="header__navigation">
            <ul class="navigation">
                <li class="navigation__item"><a href="${pageContext.request.contextPath}/jsp/contacts.jsp"
                                                class="navigation__link"><fmt:message key="label.contacts"/></a></li>
                <li class="navigation__item"><a href="${pageContext.request.contextPath}/jsp/customer/order.jsp"
                                                class="navigation__link"><fmt:message key="label.cart"/></a></li>
                <c:if test="${user.role.roleId == 2 || user.role.roleId == 1}">
                    <li class="navigation__item">
                        <form class="header__form" method="get" action="${pageContext.request.contextPath}/controller">
                            <button style="cursor: pointer; border: none; color: rgb(255, 255, 255);
                                background: rgb(0, 0, 0);" class="navigation__link" type="submit" value="PROFILE"
                                    name="command"><fmt:message key="label.profile"/>
                            </button>
                        </form>
                    </li>
                </c:if>
                <li class="navigation__item">
                    <c:choose>
                        <c:when test="${user.role.roleId == 2 || user.role.roleId == 1}">
                            <form class="header__form" method="get"
                                  action="${pageContext.request.contextPath}/controller">
                                <button style="cursor: pointer" class="navigation__link" type="submit" value="LOGOUT"
                                        name="command"/>
                                <fmt:message key="label.logout"/>
                                </button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/jsp/login.jsp" id="login"
                               class="navigation__link"><fmt:message key="label.sign_in"/></a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
            <form>
                <select id="language" name="language" onchange="submit()">
                    <option value="ru" ${language == 'ru' ? 'selected' : ''}>ru</option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>en</option>
                </select>
            </form>
        </div>
    </div>
</header>

<main class="main">
    <div class="wrapper aside__wrapper">
        <div class="aside-menu">
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <ul class="aside__list">
                    <li class="aside__item">
                        <button class="aside__button" type="submit" name="command" value="PROFILE"><fmt:message
                                key="label.your_info"/></button>
                    </li>
                    <li class="aside__item">
                        <button class="aside__button" type="submit" name="command"
                                value="VIEW_USER_ORDERS"><fmt:message
                                key="label.my_orders"/>
                        </button>
                    </li>
                    <c:if test="${user.role.roleId == 1}">
                        <li class="aside__item">
                            <button class="aside__button" type="submit" name="command"
                                    value="VIEW_ADMIN_PROFILE"><fmt:message
                                    key="label.administration"/>
                            </button>
                        </li>
                    </c:if>
                </ul>
            </form>
        </div>
        <div class="aside-container">
            <div class="orders">
                <table>
                    <tr>
                        <th><fmt:message key="label.order_id"/></th>
                        <th><fmt:message key="label.date"/></th>
                        <th><fmt:message key="label.total_price"/></th>
                        <th><fmt:message key="label.status"/></th>
                    </tr>
                    <c:forEach items="${orderList}" var="orders" varStatus="status">
                        <tr>
                            <td>${orders.orderId}</td>
                            <td>${orders.orderDate}</td>
                            <td>${orders.totalPrice}</td>
                            <td>
                                <c:if test="${orders.orderStatusId == 1}">
                                    <fmt:message key="label.order_status_unprocessed"/>
                                </c:if>
                                <c:if test="${orders.orderStatusId == 2}">
                                    <fmt:message key="label.order_status_processed"/>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

</main>

<footer class="footer">
    <div style="color: white;text-align: center;padding-top: 10px">
        <ctg:dev/>
    </div>
    <div class="wrapper footer__wrapper">
    </div>
</footer>

</body>
</html>