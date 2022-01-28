package by.epam.javatraining.restautant.entity;

import java.io.Serializable;
import java.util.Objects;

import by.epam.javatraining.restautant.builder.UserBuilder;



public class User implements Serializable {
    private static final long serialVersionUID = 4174824460567982532L;

    private int userId;
    private String login;
    private String password;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Role role;

    public User() {
    }

    public User(UserBuilder builder) {
        if (builder != null) {
            userId = builder.getId();
            login = builder.getLogin();
            password = builder.getPassword();
            email = builder.getEmail();
            phoneNumber = builder.getPhoneNumber();
            firstName = builder.getFirstName();
            lastName = builder.getLastName();
            role = builder.getRole();
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + userId;
        hash = 31 * hash + (login == null ? 0 : login.hashCode());
        hash = 31 * hash + (password == null ? 0 : password.hashCode());
        hash = 31 * hash + (email == null ? 0 : email.hashCode());
        hash = 31 * hash + (phoneNumber == null ? 0 : phoneNumber.hashCode());
        hash = 31 * hash + (firstName == null ? 0 : firstName.hashCode());
        hash = 31 * hash + (lastName == null ? 0 : lastName.hashCode());
        hash = 31 * hash + (role == null ? 0 : role.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return userId == user.userId &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(role, user.role);
    }

    @Override
    public String toString() {
        return "User[" +
                "id = " + userId +
                ", login = " + login +
                ", password = " + password +
                ", email = " + email +
                ", phoneNumber = " + phoneNumber +
                ", firstName = " + firstName +
                ", lastName = " + lastName +
                ", role = " + role +
                ']';
    }
}
