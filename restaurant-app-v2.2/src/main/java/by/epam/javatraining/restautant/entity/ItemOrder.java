package by.epam.javatraining.restautant.entity;

import java.util.Objects;

import by.epam.javatraining.restautant.builder.ItemOrderBuilder;



public class ItemOrder {
    private Position position;
    private Order order;
    private int quantity;

    public ItemOrder(ItemOrderBuilder builder) {
        position = builder.getPosition();
        order = builder.getOrder();
        quantity = builder.getQuantity();
    }

    public ItemOrder() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + quantity;
        hash = 31 * hash + (order == null ? 0 : order.hashCode());
        hash = 31 * hash + (position == null ? 0 : position.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ItemOrder itemOrder = (ItemOrder) object;
        return quantity == itemOrder.quantity &&
                Objects.equals(position, itemOrder.position) &&
                Objects.equals(order, itemOrder.order);
    }

    @Override
    public String toString() {
        return "ItemOrder[" +
                "position = " + position.getPositionId() +
                ", order = " + order.getOrderId() +
                ", quantity = " + quantity +
                ']';
    }
}
