package com.revature.project0.daos;

import com.revature.project0.models.Store;
import com.revature.project0.util.custom_exceptions.InvalidSQLException;
import com.revature.project0.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO implements CrudDAO<Store> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Store obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO stores (id, location) VALUES (?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getLocation());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }


    @Override
    public void update(Store obj) {

    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM stores WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
        }
    }

    @Override
    public Store getById(String id) {
        return null;
    }

    @Override
    public List<Store> getAll() {
        List<Store> stores = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stores");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                stores.add(new Store(rs.getString("id"), rs.getString("location")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return stores;
    }
}
