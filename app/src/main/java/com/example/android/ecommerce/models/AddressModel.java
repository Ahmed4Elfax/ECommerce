package com.example.android.ecommerce.models;

public class AddressModel {
    String userAddress;
    boolean selected;
    public AddressModel() {
    }

    public AddressModel(String userAddress, boolean selected) {
        this.userAddress = userAddress;
        this.selected = selected;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
