package com.revature.project0.ui;

//Import files from other packages

import com.revature.project0.models.User;
import com.revature.project0.services.UserService;
import com.revature.project0.ui.IMenu;

//Imports java libraries for more builtin functions
import java.util.Scanner;
import java.util.UUID;

public class StartMenu implements IMenu {

//    makes it impossible for user to switch while in the program
    private final UserService userService;

    public StartMenu(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void start() {

    }
}

