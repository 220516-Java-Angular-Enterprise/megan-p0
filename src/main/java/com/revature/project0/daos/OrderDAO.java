package com.revature.project0.daos;

import com.revature.project0.models.Order;
import com.revature.project0.util.custom_exceptions.InvalidSQLException;
import com.revature.project0.util.database.DatabaseConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements CrudDAO<Order> {
    Connection con = DatabaseConnection.getCon();


    @Override
    public void save(Order obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (id, time, price, users_id, stores_id) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getTime());
            ps.setInt(3, obj.getPrice());
            ps.setString(4, obj.getUser_id());
            ps.setString(5, obj.getStore_id());
            ps.executeUpdate();

        } catch (SQLException e) {
//            throw new RuntimeException("An error occurred when tyring to save to the database.");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }


    @Override
    public void update(Order obj) {}


    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM orders WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
        }
    }

    @Override
    public Order getById(String id) {
        Order order = new Order();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders where id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                order = new Order(rs.getString("id"), rs.getString("time"), rs.getInt("price"), rs.getString("users_id"), rs.getString("stores_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return order;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getString("id"));
                order.setTime(rs.getString("time"));
                order.setPrice(rs.getInt("price"));
                order.setUser_id(rs.getString("users_id"));
                order.setStore_id(rs.getString("stores_id"));

                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }
        return orders;
    }

    public List<Order> getOrdersByStore(String store_id) {
        List<Order> orders = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE stores_id = ?");
            ps.setString(1, store_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getString("id"));
                order.setTime(rs.getString("time"));
                order.setPrice(rs.getInt("price"));
                order.setUser_id(rs.getString("users_id"));
                order.setStore_id(rs.getString("stores_id"));

                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return orders;
    }

    public List<Order> getOrdersByUser(String user_id) {
        List<Order> orders = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE users_id = ?");
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getString("id"));
                order.setTime(rs.getString("time"));
                order.setPrice(rs.getInt("price"));
                order.setUser_id(rs.getString("users_id"));
                order.setStore_id(rs.getString("stores_id"));

                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return orders;
    }
}

