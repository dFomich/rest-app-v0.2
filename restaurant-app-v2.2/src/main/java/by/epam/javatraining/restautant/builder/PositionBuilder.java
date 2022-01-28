package by.epam.javatraining.restautant.builder;

import java.math.BigDecimal;

import by.epam.javatraining.restautant.entity.Position;
import by.epam.javatraining.restautant.entity.PositionItemGroup;

public class PositionBuilder {
	
	    private int positionId;
	    private BigDecimal itemPrice;
	    private String itemName;
	    private String positionImage;
	    private PositionItemGroup group;

	    public PositionBuilder buildId(int positionId) {
	        this.positionId = positionId;
	        return this;
	    }

	    public PositionBuilder buildItemPrice(BigDecimal itemPrice) {
	        this.itemPrice = itemPrice;
	        return this;
	    }

	    public PositionBuilder buildItemName(String itemName) {
	        this.itemName = itemName;
	        return this;
	    }

	    public PositionBuilder buildPositionImage(String image) {
	        this.positionImage = image;
	        return this;
	    }

	    public PositionBuilder buildPositionItemGroup(int groupId) {
	        group = new PositionItemGroup();
	        group.setGroupId(groupId);
	        return this;
	    }

	    public Position build() {
	        return new Position(this);
	    }

	    public int getPositionId() {
	        return positionId;
	    }

	    public BigDecimal getItemPrice() {
	        return itemPrice;
	    }

	    public String getItemName() {
	        return itemName;
	    }

	    public String getPositionImage() { return positionImage; }

	    public PositionItemGroup getGroup() {
	        return group;
	    }
	


}
