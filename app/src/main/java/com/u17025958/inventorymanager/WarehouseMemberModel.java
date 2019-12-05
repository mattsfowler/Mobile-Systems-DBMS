package com.u17025958.inventorymanager;

public class WarehouseMemberModel {

    private int id;
    private String location;
    private float capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public String toString() {
        String s = "location:" + location + "\n";
        s += "capacity:" + capacity;
        return s;
    }
}
