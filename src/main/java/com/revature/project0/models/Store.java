package com.revature.project0.models;

public class Store {
    private String id;
    private String location;

    public Store(){}


    public Store(String id, String location) {
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
