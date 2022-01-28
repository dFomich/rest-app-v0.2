package by.epam.javatraining.restautant.entity;

import java.io.Serializable;
import java.util.Objects;



public class Role implements Serializable {

    private int roleId;
    private String roleName;

    public Role() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Role role = (Role) object;
        return roleId == role.roleId &&
                Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + roleId;
        hash = 31 * hash + (roleName != null ? roleName.hashCode() : 0);

        return hash;
    }

    @Override
    public String toString() {
        return "Role[" + "roleId = " + roleId + ", roleName = " + roleName + ']';
    }
}
