package com.revature.project0.daos;

import com.revature.project0.models.Product;
import com.revature.project0.util.database.DatabaseConnection;
import com.revature.project0.util.custom_exceptions.InvalidSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements CrudDAO<Product>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Product obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO products (id, name, description, price, quantity, categories_id) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getDescription());
            ps.setInt(4, obj.getPrice());
            ps.setInt(5, obj.getQuantity());
            ps.setString(6, obj.getCategories_id());
            ps.executeUpdate();

        } catch (SQLException e) {
//            throw new RuntimeException("An error occurred when tyring to save to the database.");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void update(Product obj) {

    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
        }
    }

    @Override
    public Product getById(String id) {
        Product product = new Product();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products where id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                product = new Product(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getInt("price"), rs.getInt("quantity"), rs.getString("categories_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return product;
    }


    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                products.add(new Product(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getInt("price"), rs.getInt("quantity"), rs.getString("categories_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return products;
    }

    public List<Product> getByCat(String id) {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products where categories_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                products.add(new Product(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getInt("price"), rs.getInt("quantity"), rs.getString("categories_id")));
            }
        } catch (SQLException e) {
//            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

        return products;
    }


    public void updateProdName(String name, String id) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE products SET name = ? WHERE id = ?");
            ps.setString(1, name);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
        }
    }

    public void updateProdQuant(int quantity, String id) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE products SET quantity = ? WHERE id = ?");
            ps.setInt(1, quantity);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
        }
    }
}
