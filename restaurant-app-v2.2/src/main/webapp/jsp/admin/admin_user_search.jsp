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
    <title>user search</title>
    <style type="text/css">
        table {
            font-family: "Lato", Verdana, sans-serif;
            font-size: 13px;
            background: white;
            /*max-width: 180%;*/
            width: 900px;
            border-collapse: collapse;
            text-align: center;
            table-layout: fixed;
            margin-top: 10%;
        }

        th {
            font-weight: normal;
            color: black;
            padding: 10px 10px;
            width: 10px;
        }

        td {
            color: black;
            border-top: 1px solid black;
            padding: 10px 10px;
            width: 10px;
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
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <ul class="aside__list">
                    <li class="aside__item">
                        <button class="aside__button" type="submit" name="command" value="VIEW_ADMIN_PROFILE">
                            <fmt:message
                                    key="label.your_info"/></button>
                    </li>
                    <li class="aside__item">
                        <button class="aside__button" type="submit" name="command"
                                value="VIEW_UNCONFIRMED_ORDERS"><fmt:message key="label.unconfirmed_orders"/>
                        </button>
                    </li>
                    <li class="aside__item">
                        <button class="aside__button" type="submit" name="command"
                                value="VIEW_ALL_USERS_ORDERS"><fmt:message key="label.admin_users_all_orders"/>
                        </button>
                    </li>
                    <li class="aside__item">
                        <button class="aside__button" type="submit" name="command"
                                value="VIEW_ALL_USERS"><fmt:message key="label.admin_all_users"/>
                        </button>
                    </li>
                    <li class="aside__item">
                        <button class="aside__button" type="submit" name="command"
                                value="ENTER_FIND_USER_PAGE"><fmt:message key="label.admin_find_user"/>
                        </button>
                    </li>
                    <li class="aside__item">
                        <button class="aside__button" type="submit" name="command"
                                value="ENTER_FIND_ORDER_PAGE"><fmt:message key="label.admin_find_order"/>
                        </button>
                    </li>
                </ul>
            </form>
        </div>
        <div class="aside-container">
            <div class="orders">
                <form method="post"
                      action="${pageContext.request.contextPath}/controller">
                    <table>
                        <tr>
                            <th><fmt:message key="label.admin_find_by"/></th>
                            <th width="10px"><fmt:message key="label.user_id"/></th>
                            <th width="10px"><fmt:message key="label.login"/></th>
                            <th width="10px"><fmt:message key="label.email"/></th>
                        </tr>

                        <tr>
                            <td style="padding-top: 0px"><input class="modal-body__input" type="text"
                                                                name="userParameter"
                                                                placeholder="<fmt:message key="label.admin_search_enter_parameter"/>">
                            </td>
                            <td width="10px">
                                <div class="product__content">
                                    <button style="position: relative;left: 50%;transform: translate(-50%, 0);"
                                            class="button__accept_admin" type="submit" name="command"
                                            value="FIND_USER_BY_ID">
                                        <fmt:message key="label.admin_find"/>
                                    </button>
                                </div>
                            </td>
                            <td width="10px">
                                <div class="product__content">
                                    <button style="position: relative;left: 50%;transform: translate(-50%, 0);"
                                            class="button__accept_admin" type="submit" name="command"
                                            value="FIND_USER_BY_LOGIN">
                                        <fmt:message key="label.admin_find"/>
                                    </button>
                                </div>
                            </td>
                            <td width="10px">
                                <div class="product__content">
                                    <button style="position: relative;left: 50%;transform: translate(-50%, 0);"
                                            class="button__accept_admin" type="submit" name="command"
                                            value="FIND_USER_BY_EMAIL">
                                        <fmt:message key="label.admin_find"/>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <span style="font-size: 14px; color: red;">${searchMessage}</span>
                </form>

                <table align="left">
                    <tr>
                        <th width="5px"><fmt:message key="label.just_id"/></th>
                        <th><fmt:message key="label.user_profile_login"/></th>
                        <th><fmt:message key="label.user_profile_email"/></th>
                        <th><fmt:message key="label.user_profile_phone_number"/></th>
                        <th><fmt:message key="label.user_first_name"/></th>
                        <th><fmt:message key="label.user_last_name"/></th>
                        <th width="10px"><fmt:message key="label.user_role_id"/></th>
                        <th><fmt:message key="label.admin_users_all_orders"/></th>
                        <th><fmt:message key="label.admin_button_delete"/></th>
                    </tr>
                    <tr>
                        <td width="5px">${requestUserId.userId}</td>
                        <td>${requestUserId.login}</td>
                        <td>${requestUserId.email}</td>
                        <td>${requestUserId.phoneNumber}</td>
                        <td>${requestUserId.firstName}</td>
                        <td>${requestUserId.lastName}</td>
                        <td width="10px">${requestUserId.role.roleId}</td>
                        <td>
                            <form method="post"
                                  action="${pageContext.request.contextPath}/controller?id=${requestUserId.userId}">
                                <div class="product__content">
                                    <c:if test="${requestUserId != null}">
                                    <button style="background: darkslategray; position: relative;left: 50%;transform: translate(-50%, 0);"
                                            class="button__accept_admin" type="submit" name="command"
                                            value="VIEW_USER_ORDERS_BY_ADMIN">
                                        <fmt:message key="label.admin_users_all_orders"/>
                                    </button>
                                    </c:if>
                                    <c:if test="${requestUserId == null}">
                                        <button disabled style="background: darkslategray; position: relative;left: 50%;transform: translate(-50%, 0);"
                                                class="button__accept_admin" type="submit" name="command"
                                                value="VIEW_USER_ORDERS_BY_ADMIN">
                                            <fmt:message key="label.admin_users_all_orders"/>
                                        </button>
                                    </c:if>
                                </div>
                            </form>
                        </td>
                        <td>
                            <form method="post"
                                  action="${pageContext.request.contextPath}/controller?id=${requestUserId.userId}">
                                <div class="product__content">
                                    <c:if test="${requestUserId != null}">
                                    <button style="background: red; position: relative;left: 50%;transform: translate(-50%, 0);"
                                            class="button__accept_admin" type="submit" name="command"
                                            value="DELETE_USER_BY_ID_SEARCH_PAGE">
                                        <fmt:message key="label.admin_button_delete"/>
                                    </button>
                                    </c:if>
                                    <c:if test="${requestUserId == null}">
                                        <button disabled style="background: red; position: relative;left: 50%;transform: translate(-50%, 0);"
                                                class="button__accept_admin" type="submit" name="command"
                                                value="DELETE_USER_BY_ID_SEARCH_PAGE">
                                            <fmt:message key="label.admin_button_delete"/>
                                        </button>
                                    </c:if>
                                </div>
                            </form>
                        </td>
                    </tr>
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