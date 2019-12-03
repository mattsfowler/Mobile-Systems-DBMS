package com.u17025958.inventorymanager;

import java.io.Serializable;

public class ProductMemberModel implements Serializable {

    private int id;
    private String name;
    private float price;
    private float size;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toString() {
        String s = "name:" + name + "\n";
        s += "price:" + price  + "\n";
        s += "size:" + size  + "\n";
        s += "image:" + image;
        return s;
    }
}
