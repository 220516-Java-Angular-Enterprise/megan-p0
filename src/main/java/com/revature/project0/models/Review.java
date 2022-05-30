package com.revature.project0.models;

public class Review {
    private String id;
    private int rating;
    private String msg;
    private String user_id;
    private String product_id;

    public Review(){}


    public Review(String id, int rating, String msg, String user_id, String product_id) {
        this.id = id;
        this.rating = rating;
        this.msg = msg;
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String item_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", rating=" + rating +
                ", msg='" + msg + '\'' +
                ", user_id='" + user_id + '\'' +
                ", product_id='" + product_id + '\'' +
                '}';
    }
}
