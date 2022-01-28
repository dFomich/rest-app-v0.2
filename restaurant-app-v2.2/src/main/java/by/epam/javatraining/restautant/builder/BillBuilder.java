package by.epam.javatraining.restautant.builder;

import java.util.Date;

import by.epam.javatraining.restautant.entity.Bill;
import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.entity.User;



public class BillBuilder {
    private int billId;
    private Order order;
    private User admin;
    private Date billDate;

    public BillBuilder() {
    }

    public BillBuilder buildId(int billId) {
        this.billId = billId;
        return this;
    }

    public BillBuilder buildOrder(Order order) {
        this.order = order;
        return this;
    }

    public BillBuilder buildUser(User user) {
        admin = user;
        return this;
    }

    public BillBuilder buildDate(Date billDate) {
        this.billDate = billDate;
        return this;
    }

    public Bill build() {
        return new Bill(this);
    }

    public int getBillId() {
        return billId;
    }

    public Order getOrder() {
        return order;
    }

    public User getAdmin() {
        return admin;
    }

    public Date getBillDate() {
        return billDate;
    }
}
