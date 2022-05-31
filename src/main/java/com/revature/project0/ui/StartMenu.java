package com.revature.project0.ui;

//Import files from other packages
//daos
import com.revature.project0.daos.*;
//models
import com.revature.project0.models.User;
import com.revature.project0.models.Product;
import com.revature.project0.models.Review;
//services
import com.revature.project0.services.*;
//utils
import com.revature.project0.util.annotations.Inject;
import com.revature.project0.util.custom_exceptions.InvalidUserException;

//Imports java libraries for more builtin functions
import java.util.Scanner;
import java.util.UUID;

public class StartMenu implements IMenu {

    //    makes it impossible for user to switch while in the program
    @Inject
    private final UserService userService;

    @Inject
    public StartMenu(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void start() {

//        to initiate the scanner function so that we can use it in our code to get user input
        Scanner scan = new Scanner(System.in);

//        break label: allows for a method to break out of a loop and not keep executing even if condition is still met
        exit:
        {
            while (true) {
                displayWelcomeMsg();

//                Asking user to enter their input into the terminal
                System.out.print("\nEnter: ");
                String input = scan.nextLine();

//                Switch case: like an else statement for multiple options (used small scale with finite answers
                switch (input) {
//                    Cases for if user enters 1, 2, or x and a default for other options
                    case "1":
                        login();
                        break;
                    case "2":
                        signup();
                        break exit;
                    case "x":
                        System.out.println("\nGoodbye! Looking forward to seeing you again soon");
//                        will break out of the break label method
                        break exit;
                    default:
                        System.out.println("\nInvalid input, please try again");
                        break;
                }
            }
        }
    }

    private void displayWelcomeMsg() {
//        Welcome message/user prompts
        System.out.println("\nWelcome to my store!");
        System.out.println("[1] Login");
        System.out.println("[2] Signup");
        System.out.println("[x] Exit");
    }

    private void login() {
        String username;
        String password;
        User user = new User();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\nLogging in...");
            System.out.print("\nUsername: ");
            username = scan.nextLine();

            System.out.print("\nPassword: ");
            password = scan.nextLine();

            try {
                user = userService.login(username, password);

//                need to add parameters to Admin menu function/main menu
                if (user.getRole().equals("ADMIN")) new AdminMenu(user, new ProductService(new ProductDAO()), new StoreService(new StoreDAO())).start();
                else
                    new MainMenu(user, new UserService(new UserDAO()), new ReviewService(new ReviewDAO()), new ProductService(new ProductDAO()), new CategoryService(new CategoryDAO())).start();
                break;
            } catch (InvalidUserException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void signup() {
//        creating global variables so that they can be used anywhere in the StartMenu Class
        String username;
        String password;
        String fname;
        String role;
        String email;
        String phone;
        String sAddress;
        String state;
//        need to recreate the scanner method in this function so that it can be used to take in input.
        Scanner scan = new Scanner(System.in);

//        creating a nested break label so that we can choose where to break out
        completeExit:
        {
            while (true) {
                System.out.println("\nCreating account...");

//            Asking for username
                while (true) {
                    System.out.print("\nUsername: ");
//                    changes the value of the username variable
                    username = scan.nextLine();

//                    checks against regex to make sure username is valid
                    try {
                        if (userService.isValidUsername(username))
                            if (userService.isNotDuplicateUsername(username)) break;
                    } catch (InvalidUserException e) {
                        System.out.println((e.getMessage()));
                    }
                }

//                creating/confirming password
                while (true) {
                    System.out.print("\nPassword: ");
//                    changes the value of password variable
                    password = scan.nextLine();

                    try {
//                    checks against regex to see if password is valid
                        if (userService.isValidPassword(password)) {
                            System.out.print("\nReEnter Password: ");
//                        creates a confirm variable to check against password
                            String confirm = scan.nextLine();

                            if (password.equals(confirm)) break;
                            else System.out.println("Password does not match...");
                        }
                    } catch (InvalidUserException e) {
                        System.out.println(e.getMessage());
                    }
                }

                while (true) {
                    /*Asking user to enter in first name.*/
                    System.out.print("\nFirst Name: ");
                    fname = scan.nextLine();

                    break ;
                }

                while (true) {
                    /*Asking user to enter in username.*/
                    System.out.print("\nEmail: ");
                    email = scan.nextLine();
                    break;
                }

                while (true) {
                    /*Asking user to enter in username.*/
                    System.out.print("\nPhone Number: ");
                    phone = scan.nextLine();
                    break;
                }

                while (true) {
                    /*Asking user to enter in username.*/
                    System.out.print("\nStreet Address: ");
                    sAddress = scan.nextLine();
                    break;
                }

                while (true) {
                    /*Asking user to enter in username.*/
                    System.out.print("\nState: ");
                    state = scan.nextLine();
                    break;
                }


//                nested break label. Will allow for user to go back and add credentials again if answer "n"
                    confirmExit:
                    {
                        while (true) {
                            System.out.println("\nPlease confirm your credentials (y/n)");
                            System.out.println("\nUsername: " + username);
                            System.out.println("\nPassword: " + password);
                            System.out.println("\nFirst Name: " + fname);
                            System.out.println("\nEmail: " + email);
                            System.out.println("\nPhone Number: " + phone);
                            System.out.println("\nStreet Address: " + sAddress);
                            System.out.println("\nState: " + state);



                            System.out.print("\nEnter (y/n): ");
                            String input = scan.nextLine();

                            switch (input) {
                                case "y":
                                    User user = new User(UUID.randomUUID().toString(), username, password, "DEFAULT", fname, email, phone, sAddress, state);
                                    userService.register(user);
                                    new MainMenu(user, new UserService(new UserDAO()), new ReviewService(new ReviewDAO()), new ProductService(new ProductDAO()), new CategoryService(new CategoryDAO())).start();
                                    break completeExit;
                                case "n":
                                    break confirmExit;
                                default:
                                    System.out.println("Invalid input, please try again...");
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }


