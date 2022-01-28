package by.epam.javatraining.restautant.entity;

import java.util.Objects;



public class PositionItemGroup {
    private int groupId;
    private String groupName;

    public PositionItemGroup() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + groupId;
        hash = 31 * hash + (groupName == null ? 0 : groupName.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PositionItemGroup that = (PositionItemGroup) object;
        return groupId == that.groupId &&
                Objects.equals(groupName, that.groupName);
    }

    @Override
    public String toString() {
        return "ItemGroup[" + " id =" + groupId + ", groupName = " + groupName + ']';
    }
}
