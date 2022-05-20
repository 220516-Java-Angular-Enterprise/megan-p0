package com.revature.project0.models;

//Create the blueprint for User class
public class User {
    private String id;
    private String username;
    private String password;
    private String role;

    //    Create a new instance of the User for use in methods
    public User() {
    }

    // Constructor for User Class (what info goes in)
    public User(String id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //    Getters/Setters so that data can be retrieved and manipulated
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toFileString() {
        return id + ":" + username + ":" + password + ":" + role + "\n";
    }

    //    Allows for readable info to display in terminal instead of the Hashcode
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

