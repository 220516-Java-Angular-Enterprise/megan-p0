package com.revature.project0.models;

public class Product_Order {
    private String product_id;
    private String order_id;

    public Product_Order() {
    }

    public Product_Order(String product_id, String order_id) {
        this.product_id = product_id;
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "Product_Order{" +
                "product_id='" + product_id + '\'' +
                ", order_id='" + order_id + '\'' +
                '}';
    }
}


