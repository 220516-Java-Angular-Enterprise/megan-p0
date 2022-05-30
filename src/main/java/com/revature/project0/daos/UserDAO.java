package com.revature.project0.daos;

import com.revature.project0.models.User;
import com.revature.project0.util.custom_exceptions.InvalidSQLException;
import com.revature.project0.util.database.DatabaseConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(User obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, email, phone, sAddress, state) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getPhone());
            ps.setString(6, obj.getsAddress());
            ps.setString(7, obj.getState());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
        }
    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(new User(rs.getString("id"), rs.getString("username"), rs.getString("role"), rs.getString("email"), rs.getString("phone"), rs.getString("sAddress"), rs.getString("state")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return users;
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT username FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usernames.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return usernames;
    }

    public User getUserByUsernameAndPassword(String un, String pw) {
//        User user = new User();
//
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(path));
//
//            String userData; // id:username:password:role
//            while ((userData = br.readLine()) != null) {
//                String[] userArr = userData.split(":"); // [id, username, password, role]
//                String id = userArr[0];
//                String username = userArr[1];
//                String password = userArr[2];
//                String role = userArr[3];
//
//                if (un.equals(username)) {
//                    user.setId(id);
//                    user.setUsername(username);
//                    user.setRole(role);
//
//                    if (pw.equals(password)) user.setPassword(password);
//                    else break;
//                } else if (pw.equals(password)) user.setPassword(password);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException("An error occurred when trying to access the file.");
//        } catch (IOException e) {
//            throw new RuntimeException("An error occurred when trying to access the file information.");
//        }

        return null;
    }

    public void updateAddress(String sAddress, String state, String id) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE users SET (sAddress, state) = (?, ?) WHERE id = ?");
            ps.setString(1, sAddress);
            ps.setString(2, state);
            ps.setString(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
        }
    }

    public void updatePhone(String phone, String id) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE users SET phone = ? WHERE id = ?");
            ps.setString(1, phone);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
        }
    }

    public void updateEmail(String email, String id) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE users SET email = ? WHERE id = ?");
            ps.setString(1, email);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to update data from to the database.");
        }
    }

}