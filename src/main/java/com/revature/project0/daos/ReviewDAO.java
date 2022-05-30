package com.revature.project0.daos;

import com.revature.project0.models.Review;
import com.revature.project0.util.custom_exceptions.InvalidSQLException;
import com.revature.project0.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO implements CrudDAO<Review> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Review obj) {

    }

    @Override
    public void update(Review obj) {

    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM reviews WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
        }
    }

    @Override
    public Review getById(String id) {
        return null;
    }

    @Override
    public List<Review> getAll() {
        return null;
    }

    public List<Review> getReviewsByProdId(String prod_id) {
        List<Review> reviews = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reviews WHERE product_id = (?)");
            ps.setString(1, prod_id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                reviews.add(new Review(rs.getString("id"), rs.getInt("rating"), rs.getString("msg"), rs.getString("user_id"), rs.getString("product_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return reviews;
    }
}
