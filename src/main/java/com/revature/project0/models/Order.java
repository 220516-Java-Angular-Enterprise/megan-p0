package com.revature.project0.models;

public class Order {
    private String id;
    private String time;
    private int price;
    private String user_id;
    private String store_id;

    public Order(){}

    public Order(String id, String time, int price, String user_id, String store_id) {
        this.id = id;
        this.time = time;
        this.price = price;
        this.user_id = user_id;
        this.store_id = store_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", price=" + price +
                ", user_id='" + user_id + '\'' +
                ", store_id='" + store_id + '\'' +
                '}';
    }
}
