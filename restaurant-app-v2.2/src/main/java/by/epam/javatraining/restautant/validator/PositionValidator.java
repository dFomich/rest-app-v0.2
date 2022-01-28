package by.epam.javatraining.restautant.validator;

import by.epam.javatraining.restautant.entity.Position;

public enum PositionValidator {

    INSTANCE;

    private static final String POSITION_NAME_PATTERN = "^[A-Za-z0-9_-]{2,16}$";

    public boolean isPositionValidate(Position position) {
        return position != null
                && position.getItemName().matches(POSITION_NAME_PATTERN)
                && position.getGroup().getGroupId() > 0
                && position.getItemPrice().doubleValue() > 0;
    }

    public boolean isIdValidate(int id) {
        return id > 0;
    }
}
