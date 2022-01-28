package by.epam.javatraining.restautant.entity;

import java.util.Date;
import java.util.Objects;

import by.epam.javatraining.restautant.builder.BillBuilder;



public class Bill {
    private int billId;
    private Order order;
    private User admin;
    private Date billDate;

    public Bill(BillBuilder billBuilder) {
        billId = billBuilder.getBillId();
        order = billBuilder.getOrder();
        admin = billBuilder.getAdmin();
        billDate = billBuilder.getBillDate();
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + billId;
        hash = 31 * hash + (order == null ? 0 : order.hashCode());
        hash = 31 * hash + (admin == null ? 0 : admin.hashCode());
        hash = 31 * hash + (billDate == null ? 0 : billDate.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Bill bill = (Bill) object;
        return billId == bill.billId &&
                Objects.equals(order, bill.order) &&
                Objects.equals(admin, bill.admin) &&
                Objects.equals(billDate, bill.billDate);
    }

    @Override
    public String toString() {
        return  "Order ID: " + order.getOrderId() + "\n" +
                "Administrator: " + admin.getFirstName() + "\n" +
                "Date: " + billDate + "\n" +
                "Total sum: " + order.getTotalPrice() + "\n" +
                "Delivery address: " + order.getDeliveryAddress().getStreet() + " "
                + order.getDeliveryAddress().getBuildNumber() + "-" + order.getDeliveryAddress().getApartmentNumber();
    }
}
