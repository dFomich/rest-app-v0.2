package by.epam.javatraining.restautant.entity;

import java.math.BigDecimal;
import java.util.Objects;

import by.epam.javatraining.restautant.builder.PositionBuilder;



public class Position {
    private int positionId;
    private BigDecimal itemPrice;
    private String itemName;
    private String positionImage;
    private PositionItemGroup group;

    public Position(PositionBuilder builder) {
        this.positionId = builder.getPositionId();
        this.itemPrice = builder.getItemPrice();
        this.itemName = builder.getItemName();
        this.positionImage = builder.getPositionImage();
        this.group = builder.getGroup();
    }

    public Position() {
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public PositionItemGroup getGroup() {
        return group;
    }

    public void setGroup(PositionItemGroup group) {
        this.group = group;
    }

    public String getPositionImage() {
        return positionImage;
    }

    public void setPositionImage(String positionImage) {
        this.positionImage = positionImage;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + positionId;
        hash = 31 * hash + (itemPrice == null ? 0 : itemPrice.hashCode());
        hash = 31 * hash + (itemName == null ? 0 : itemName.hashCode());
        hash = 31 * hash + (group == null ? 0 : group.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Position position = (Position) object;
        return positionId == position.positionId &&
                Objects.equals(itemPrice, position.itemPrice) &&
                Objects.equals(itemName, position.itemName) &&
                Objects.equals(group, position.group);
    }

    @Override
    public String toString() {
        return "Position[" +
                "id = " + positionId +
                ", itemPrice = " + itemPrice +
                ", itemName = " + itemName +
                ", group = " + group +
                ']';
    }
}
