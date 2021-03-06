package com.example.android.ecommerce.models;

import java.io.Serializable;

public class PopularProductsModel implements Serializable {
    String img_url;
    String description;
    String name;
    String rating;
    double price;

    public PopularProductsModel(String img_url, String description, String name, String rating, double price) {
        this.img_url = img_url;
        this.description = description;
        this.name = name;
        this.rating = rating;
        this.price = price;
    }

    public PopularProductsModel() {
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
