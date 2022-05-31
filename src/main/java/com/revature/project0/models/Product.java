package com.revature.project0.models;

public class Product {
    private String id;
    private String name;
    private String description;
    private int price;
    private int quantity;

    private String categories_id;

    public Product() {}


    public Product(String id, String name, String description, int price, int quantity, String categories_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.categories_id = categories_id;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategories_id() {
        return categories_id;
    }

    public void setCategory_id(String categories_id) {
        this.categories_id = categories_id;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category_id='" + categories_id + '\'' +
                '}';
    }
}