package by.epam.javatraining.restautant.dao;

public enum DBFields {
    DB_USER_ID("user_id"),
    DB_USER_LOGIN("login"),
    DB_USER_PASSWORD("password"),
    DB_USER_EMAIL("email"),
    DB_USER_PHONE_NUMBER("phone_number"),
    DB_USER_FIRST_NAME("first_name"),
    DB_USER_LAST_NAME("last_name"),
    DB_USER_ROLE_ID("role_id"),
    DB_ORDER_ID("order_id"),
    DB_ORDER_DATE("order_date"),
    DB_ORDER_CUSTOMER_ID("customer_id"),
    DB_ORDER_TOTAL_PRICE("total_price"),
    DB_ORDER_STATUS("order_status"),
    DB_ORDER_DELIVERY_ADDRESS_ID("id_delivery_address"),
    DB_POSITIONS_ITEM_ID("item_id"),
    DB_POSITIONS_ITEM_NAME("item_name"),
    DB_POSITIONS_GROUP_ID("group_id"),
    DB_POSITIONS_ITEM_PRICE("item_price"),
    DB_POSITION_ITEM_IMAGE("pos_image"),
    DB_ADDRESS_ID("delivery_address_id"),
    DB_ADDRESS_STREET("street"),
    DB_ADDRESS_BUILD("build"),
    DB_ADDRESS_APARTMENT("apartment"),
    DB_ITEM_ORDER_ORDER_ID("order_id"),
    DB_ITEM_ORDER_ITEM_ID("item_id"),
    DB_ITEM_ORDER_QUANTITY("quantity");

    private String value;

    DBFields(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
