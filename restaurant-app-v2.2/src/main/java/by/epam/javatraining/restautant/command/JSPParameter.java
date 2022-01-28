package by.epam.javatraining.restautant.command;

public enum JSPParameter {

    COMMAND("command"),
    LOGIN("login"),
    PASSWORD("password"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    PHONE_NUMBER("phoneNumber"),
    EMAIL("email"),
    USER("user"),
    ROLE("role"),
    POSITIONS("positionList"),
    ID("id"),
    SESSION_POSITIONS("positions"),
    ORDER("order"),
    STREET("street"),
    BUILD_NUMBER("build"),
    APARTMENTS_NUMBER("number"),
    USER_ORDER_LIST("orderList"),
    UNCONFIRMED_ORDERS("unconfirmedOrderList"),
    ALL_USERS_ORDERS("allUsersOrders"),
    ALL_USERS("userList"),
    ITEM_ORDER_LIST("itemOrderList"),
    USER_ID("userId"),
    USER_ID_REQUEST("requestUserId"),
    LOGIN_ERROR("loginError"),
    REGISTRATION_ERROR("registrationError"),
    USER_SEARCH_MESSAGE("searchMessage"),
    USER_SEARCH_PARAMETER("userParameter"),
    ORDER_SEARCH_MESSAGE("orderSearchMessage"),
    ORDER_SEARCH_PARAMETER("orderParameter"),
    CONFIRM_ORDER("CONFIRM_ORDER"),
    REGISTRATION("REGISTRATION"),
    REQUEST_TYPE("requestType"),
    POST(" POST");

    private String value;

    JSPParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
