package com.revature.project0.models;

//Create the blueprint for User class
public class User {
    private String id;
    private String username;
    private String password;

    private String fname;
    private String role;
    private String email;
    private String phone;
    private String sAddress;
    private String state;

    //    Create a new instance of the User for use in methods
    public User() {
    }

    // Constructor for User Class (what info goes in)
    public User(String id, String username, String password, String role, String fname, String email, String phone, String sAddress, String state) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.sAddress = sAddress;
        this.state = state;
    }

//    public User(String id, String username, String password, String role, String fname, String email, String phone, String sAddress, String state) {
//    }

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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
                ", password='" + password + '\'' +
                ", first name='" + fname + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sAddress='" + sAddress + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

