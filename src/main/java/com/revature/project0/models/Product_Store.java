package com.revature.project0.models;

public class Product_Store {
    private String store_id;
    private String product_id;

    public Product_Store() {}

    public Product_Store(String store_id, String order_id) {
        this.store_id = store_id;
        this.product_id = order_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getOrder_id() {
        return product_id;
    }

    public void setOrder_id(String order_id) {
        this.product_id = order_id;
    }

    @Override
    public String toString() {
        return "Product_Store{" +
                "store_id='" + store_id + '\'' +
                ", order_id='" + product_id + '\'' +
                '}';
    }
}
