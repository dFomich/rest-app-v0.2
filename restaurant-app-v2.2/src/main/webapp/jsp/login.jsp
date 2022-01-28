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
    <title>login page</title>
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
                <li class="navigation__item"><a href="#" class="navigation__link"><fmt:message
                        key="label.contacts"/></a></li>
                <li class="navigation__item"><a href="${pageContext.request.contextPath}/jsp/customer/order.jsp"
                                                class="navigation__link"><fmt:message key="label.cart"/></a></li>
                <li class="navigation__item"><a id="login" href="${pageContext.request.contextPath}/jsp/login.jsp"
                                                class="navigation__link"><fmt:message key="label.sign_in"/></a></li>
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
        <div class="modal-window">
            <div class="modal-header">
                <h3 class="modal-title"><fmt:message key="label.login"/></h3>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input class="modal-body__input" type="text" name="login"
                           placeholder="<fmt:message key="label.username"/>">
                    <input class="modal-body__input" type="password" name="password"
                           placeholder="<fmt:message key="label.password"/>">
                    <span style="font-size: 12px; color: red">${loginError}</span>
                    <button class="modal-body__input button-login" type="submit" name="command" value="SIGN_IN">
                        <fmt:message key="label.login"/>
                    </button>
                </form>
            </div>
            <div class="modal-footer">
                <form action="${pageContext.request.contextPath}/jsp/registration.jsp">
                    <button class="modal-body__input button-sign-up" type="submit"><fmt:message key="label.sign_up"/></button>
                </form>
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
