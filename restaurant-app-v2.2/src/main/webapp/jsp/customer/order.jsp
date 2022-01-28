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
    <script src="${pageContext.request.contextPath}/js/countSelector.js"></script>
    <title>restaurant</title>
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
                                        name="command"/><fmt:message key="label.logout"/>
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
    <div class="wrapper main__wrapper">
        <div class="orders">
            <ul class="orders__list">
                <c:forEach items="${positions}" var="positions" varStatus="status">
                    <li class="order__item">
                        <img src="data:image/jpg;base64,${positions.key.positionImage}" width="50" height="50"/>
                        <div class="order__plus__name">
                            <span class="order__text">${positions.key.itemName}</span>
                        </div>
                        <div class="buttons">
                            <button class="order__button button_danger">-</button>
                            <span class="order__quantity">${positions.value}</span>
                            <button class="order__button button_success">+</button>
                        </div>
                        <c:set var="quantity" scope="session" value="${positions.value}"/>
                        <c:set var="price" scope="session" value="${positions.key.itemPrice}"/>
                        <span id="total" class="order__text" style="width: 45px;"><c:out value="${quantity * price}"/></span>
                    </li>
                </c:forEach>
            </ul>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <button style="position: relative;left: 50%;transform: translate(-50%, 0);" class="confirm-button"
                        type="submit" name="command" value="CREATE_ORDER"><fmt:message key="label.order"/>
                </button>
            </form>
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

<script src="${pageContext.request.contextPath}/js/countSelector.js"></script>

</body>

</html>