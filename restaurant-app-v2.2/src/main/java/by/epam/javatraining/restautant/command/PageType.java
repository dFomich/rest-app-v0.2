package by.epam.javatraining.restautant.command;

public enum PageType {

    REGISTRATION_PAGE("/jsp/registration.jsp"),
    START_PAGE("/jsp/start_page.jsp"),
    SIGN_IN_PAGE("/jsp/login.jsp"),
    CART_PAGE("/jsp/customer/order.jsp"),
    CONTACTS_PAGE("/jsp/contacts.jsp"),
    PROFILE_PAGE("jsp/customer/profile.jsp"),
    ADDRESS_FORM_PAGE("/jsp/customer/address.jsp"),
    PROFILE_USER_ORDERS("/jsp/customer/profile_user_orders.jsp"),
    ADMIN_START_PAGE("/jsp/admin/admin_start_page.jsp"),
    ADMIN_UNCONFIRMED_ORDERS_PAGE("/jsp/admin/admin_unconfirmed_orders.jsp"),
    ADMIN_ALL_USERS_ORDERS("/jsp/admin/admin_all_orders.jsp"),
    ADMIN_ALL_USERS("/jsp/admin/admin_all_users.jsp"),
    ADMIN_USER_SEARCH("/jsp/admin/admin_user_search.jsp"),
    ADMIN_ORDER_SEARCH("/jsp/admin/admin_order_search.jsp"),
    ADMIN_ORDER_INFO("/jsp/admin/admin_order_info.jsp"),
    ADMIN_USER_ORDERS("/jsp/admin/admin_user_orders.jsp"),
    SUCCESSFUL_REGISTRATION_MESSAGE("/jsp/registration_successful.jsp"),
    SUCCESSFUL_ORDER("/jsp/customer/order_successful.jsp");

    private String value;

    PageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}