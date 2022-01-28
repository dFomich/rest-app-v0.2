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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/orders.css">
    <%@ taglib prefix="ctg" uri="customtags" %>
    <title>My restaurant</title>
</head>

<body>
<header class="header">
    <div class="wrapper header__wrapper">
        <div class="header__logo-line">
            <a href="${pageContext.request.contextPath}/jsp/start_page.jsp" class="logo">
                <h1 class="logo__text" id="restaurant"><fmt:message key="label.restaurant"/></h1>
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
        <h1 style="width:100%;top:50%;text-align:center;margin-top: 20%">
            <fmt:message key="label.error"/> ${pageContext.errorData.statusCode}
            <c:if test="${pageContext.errorData.statusCode == 404}">
                <fmt:message key="label.error_404_message"/>
            </c:if>
            <c:if test="${pageContext.errorData.statusCode == 500}">
                <fmt:message key="label.error_500_message"/>
            </c:if>
            <c:if test="${pageContext.errorData.statusCode == 400}">
                <fmt:message key="label.error_400_message"/>
            </c:if>
            <c:if test="${pageContext.errorData.statusCode == 429}">
                <fmt:message key="label.error_429_message"/>
            </c:if>
            <c:if test="${pageContext.errorData.statusCode == 403}">
                <fmt:message key="label.error_403_message"/>
            </c:if>
            <c:if test="${pageContext.errorData.statusCode == 502}">
                <fmt:message key="label.error_502_message"/>
            </c:if>
            <c:if test="${pageContext.errorData.statusCode == 502}">
                <fmt:message key="label.error_502_message"/>
            </c:if>
            <c:if test="${pageContext.errorData.statusCode == 503}">
                <fmt:message key="label.error_503_message"/>
            </c:if>
            <c:if test="${pageContext.errorData.statusCode == 504}">
                <fmt:message key="label.error_504_message"/>
            </c:if>
        </h1>
    </div>
</main>

<footer class="footer">
    <div style="color: white;text-align: center;padding-top: 10px">
        <ctg:dev/>
    </div>
    <div class="wrapper footer__wrapper">
    </div>
</footer>

<script>
    <%@include file="/js/index.js" %>
</script>
</body>

