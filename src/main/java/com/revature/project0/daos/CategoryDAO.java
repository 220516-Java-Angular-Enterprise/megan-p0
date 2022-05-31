package com.revature.project0.daos;

import com.revature.project0.models.Category;
import com.revature.project0.util.custom_exceptions.InvalidSQLException;
import com.revature.project0.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements CrudDAO<Category> {

    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Category obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO stores (id, name) VALUES (?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Category obj) {

    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM categories WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
        }
    }

    @Override
    public Category getById(String id) {
        Category cat = new Category();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM categories where id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cat = new Category(rs.getString("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return cat;
    }

    @Override
    public List<Category> getAll() {
        List<Category> cats = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM categories");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cats.add(new Category(rs.getString("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }
        return cats;
    }
}
