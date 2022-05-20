package com.revature.project0.services;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.User;
import com.revature.project0.util.annotations.Inject;
import com.revature.project0.util.custom_exceptions.InvalidUserException;

import java.util.List;

//        Purpose: validation (checks username, password and retrieves data from daos (Data Access Object System))
public class UserService {

    @Inject
    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String username, String password) {
        User user = userDAO.getUserByUsernameAndPassword(username, password);

        if (isValidCredentials(user)) return user;

        return null;
    }

    public void register(User user) {
        userDAO.save(user);
    }

    public boolean isValidUsername(String username) {
        if (username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) return true;
        throw new InvalidUserException("Invalid Username. Must have 8-20 characters.");
    }

    public boolean isNotDuplicateUsername(String username) {
        List<String> usernames = userDAO.getAllUsernames();

        if (usernames.contains(username)) throw new InvalidUserException("Username is already taken :(");

        return true;
    }

    public boolean isValidPassword(String password) {
        if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) return true;

        throw new InvalidUserException("Invalid password. Minimum eight characters, at least one letter, one number and one special character.");
    }

    private boolean isValidCredentials(User user) {

        if (user.getUsername() == null && user.getPassword() == null) throw new InvalidUserException("Incorrect username and password.");
        else if (user.getUsername() == null) throw new InvalidUserException("Incorrect username.");
        else if (user.getPassword() == null) throw new InvalidUserException("Incorrect password.");

        return true;
    }
}
