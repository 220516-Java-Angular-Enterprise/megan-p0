package com.revature.project0.ui;

import com.revature.project0.models.Category;
import com.revature.project0.models.Product;
import com.revature.project0.models.Review;
import com.revature.project0.models.User;
import com.revature.project0.services.CategoryService;
import com.revature.project0.services.ProductService;
import com.revature.project0.services.ReviewService;
import com.revature.project0.services.UserService;
import com.revature.project0.util.annotations.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu implements IMenu {

    @Inject
//    makes it impossible for user to switch while in the program
    private final User user;
    private final UserService userService;
    private final ReviewService reviewService;
    private final ProductService productService;

    private final CategoryService categoryService;

    public MainMenu(User user, UserService userService, ReviewService reviewService, ProductService productService, CategoryService categoryService) {
        this.user = user;
        this.userService = userService;
        this.reviewService = reviewService;
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("Welcome to main menu " + user.getFname());
                System.out.println("[1] View all products");
                System.out.println("[2] View products by category");
                System.out.println("[3] View past orders");
                System.out.println("[4] Update Profile");
                System.out.println("[x] Sign out");

                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        viewAllProds();
                        break;
                    case "2":
                        viewByCat();
                        break;
                    case "3":
                        viewPastOrder();
                        break;
                    case "4":
                        updateProfile();
                        break;
                    case "x":
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }

//    make helper functions, it's getting too big to handle
    public void viewAllProds() {
        Scanner scan = new Scanner(System.in);
        List<Product> prods = productService.getAllProd();
        List<Product> cart = new ArrayList<>();

        exit:
        {
            while (true) {
                System.out.println("Please select a product");

                for (int i = 0; i < prods.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + prods.get(i).getName());
                }
                System.out.print("Enter: ");
                int input = scan.nextInt() - 1;

                if (input >= 0 && input < prods.size()) {
                    Product selectedProd = prods.get(input);
                    Product prod = productService.getById(selectedProd.getId());
                    cart.add(prod);
                }

            }
        }
    }

    private void selection() {
        Scanner scan = new Scanner(System.in);

        System.out.println("[1] Add to order");
        System.out.println("[2] Read reviews");
        System.out.println("[3] Write a review");
        System.out.println("[x] Exit");

        System.out.println("Enter: ");

    }

    private void addToOrder(List<Product> prods) {

    }

    private void readReview() {}

    private void leaveReview() {}

    private void viewAllProd() {
        Scanner scan = new Scanner(System.in);
        List<Product> prod = productService.getAllProd();

        completeExit:
        {
            while (true) {
                System.out.println("Please select a product");

                for (int i = 0; i < prod.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + prod.get(i).getName());
                }

                System.out.print("\nEnter: ");
                int input = scan.nextInt() - 1;

                if (input >= 0 && input < prod.size()) {
                    Product selectedProd = prod.get(input);
                    List<Review> reviews = reviewService.getReviewByProd(selectedProd.getId());

                    System.out.println("Reviews for " + selectedProd.getName());

                    if (!reviews.isEmpty()) {
                        exit:
                        {
                            while (true) {
                                int newLine = 0;
                                for (Review r : reviews) {
                                    User userReview = userService.getUserById(r.getUser_id());
                                    System.out.println(r.getMsg() + "\nRating: " + r.getRating() + "\nUser: " + userReview.getUsername());

                                    if (newLine < reviews.size() - 1) System.out.println();

                                    newLine++;
                                }

                                scan.nextLine();

                                System.out.println("\nDo you want to leave a review? (y/n)");
                                System.out.print("\nEnter: ");

                                switch (scan.nextLine()) {
                                    case "y":
                                        break;
                                    case "n":
                                        break exit;
                                    default:
                                        System.out.println("\nInvalid input!");
                                        break;
                                }
                            }
                        }
                    } else {
                        exit:
                        {
                            scan.nextLine();

                            System.out.println("No reviews yet!");
                            System.out.println("\nDo you want to leave a review? (y/n)");
                            System.out.print("\nEnter: ");

                            switch (scan.nextLine()) {
                                case "y":
                                    break;
                                case "n":
                                    break exit;
                                default:
                                    System.out.println("\nInvalid input!");
                                    break;
                            }
                        }
                    }
                } else {
                    System.out.println("\nInvalid product selection.");
                }
            }
        }
    }

    private void viewByCat() {
        Scanner scan = new Scanner(System.in);
//        select cat first then products
        List<Category> cat = categoryService.getAllCat();

        while (true) {
            System.out.println("Please select a category");

            for (int i = 0; i < cat.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + cat.get(i).getName());
            }

            System.out.print("\nEnter: ");
            int input = scan.nextInt() - 1;

            if (input >= 0 && input < cat.size()) {
                Category selectedCat = cat.get(input);
                List<Product> prods = productService.getAllByCat(selectedCat.getId());

                System.out.println("All " + selectedCat.getName());

                if (!prods.isEmpty()) {

                    exit:
                    {
                        while (true) {
                            System.out.println("Please select a product");

                            for (int i = 0; i < prods.size(); i++) {
                                System.out.println("[" + (i + 1) + "] " + prods.get(i).getName());
                            }

                            System.out.print("\nEnter: ");
                            int prodInput = scan.nextInt() - 1;

                            if (prodInput >= 0 && prodInput < prods.size()) {
                                Product selectedProd = prods.get(prodInput);
//                                productChoice();
                                break;
                           }
                        }
                    }
                }
            }


        }


    }

    private void viewPastOrder() {
    }

    private void updateProfile() {
    }

//    private
}



