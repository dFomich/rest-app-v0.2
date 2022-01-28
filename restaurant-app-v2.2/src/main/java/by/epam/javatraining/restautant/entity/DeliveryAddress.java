package by.epam.javatraining.restautant.entity;

import java.util.Objects;


public class DeliveryAddress {
    private int deliveryAddressId;
    private int buildNumber;
    private int apartmentNumber;
    private String street;

    public DeliveryAddress() {
    }

    public int getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(int deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(int buildNumber) {
        this.buildNumber = buildNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + deliveryAddressId;
        hash = 31 * hash + buildNumber;
        hash = 31 * hash + apartmentNumber;
        hash = 31 * hash + (street == null ? 0 : street.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DeliveryAddress that = (DeliveryAddress) object;
        return buildNumber == that.buildNumber &&
                apartmentNumber == that.apartmentNumber &&
                Objects.equals(street, that.street);
    }

    @Override
    public String toString() {
        return "[" +
                "deliveryAddressId = " + deliveryAddressId +
                ", buildNumber = " + buildNumber +
                ", apartmentNumber = " + apartmentNumber +
                ", street = " + street +
                ']';
    }
}
