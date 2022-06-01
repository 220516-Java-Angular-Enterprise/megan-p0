package com.revature.project0.ui;

import com.revature.project0.models.*;
import com.revature.project0.services.*;
import com.revature.project0.util.annotations.Inject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MainMenu implements IMenu {

    @Inject
//    makes it impossible for user to switch while in the program
    private final User user;
    private final UserService userService;
    private final ReviewService reviewService;
    private final ProductService productService;

    private final CategoryService categoryService;

    private final OrderService orderService;

    private final StoreService storeService;

    public MainMenu(User user, UserService userService, ReviewService reviewService, ProductService productService, CategoryService categoryService, OrderService orderService, StoreService storeService) {
        this.user = user;
        this.userService = userService;
        this.reviewService = reviewService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.orderService = orderService;
        this.storeService = storeService;
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
                        viewPastOrder(user.getId());
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

        completeExit:
        {
//            prints a list of products
            while (true) {
                System.out.println("Please select a product");

                for (int i = 0; i < prods.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + prods.get(i).getName());
                }
                System.out.print("Enter: ");
                int input = scan.nextInt() - 1;

            exit2:
            {
                if (input >= 0 && input < prods.size()) {
                    Product selectedProd = prods.get(input);
                    Product prod = productService.getById(selectedProd.getId());
                    System.out.println("Your chosen product is " + prod.getName());

//                    selection();
                    System.out.println("[1] Add to order");
                    System.out.println("[2] Read reviews");
                    System.out.println("[3] Write a review");
                    System.out.println("[x] Exit");

                    System.out.print("\nEnter: ");
                    String select = scan.nextLine();

                    switch (scan.nextLine()) {
                        case "1":
                            addToOrder(prod);
                            break;
                        case "2":
                            readReview();
                            break;
                        case "3":
                            leaveReview();
                            break;
                        case "x":
                            break completeExit;
                        default:
                            System.out.println("\nInvalid input.");
                            break;
                    }
                }
            }
            }
            }
        }


    private void addToOrder(Product prod) {
        Scanner scan = new Scanner(System.in);
        String prodID = prod.getId();
        int quantity;
        String randomOrderId = UUID.randomUUID().toString();
        List<String> cart = new ArrayList<>();
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = dateFormat.format(date);

        exit:
        {
            while(true) {
                System.out.println("How many " + prod.getName() + "'s would you like: ");
                quantity = scan.nextInt();
                int prodInventory = prod.getQuantity();
                if (prodInventory >= quantity) {
                    int price = prod.getPrice() * quantity;
                    String user_id = user.getId();
                    Order order = new Order(randomOrderId, time, price,user_id, "06218a86-4d08-4d34-88a2-421171700982");
                    orderService.register(order);

                    System.out.println("order made successfully");
                    break;

                } else System.out.println("\nThere are only " + prodInventory + " left in stock"); break;
            }
        }
    }

    private void readReview() {}

    private void leaveReview() {}

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

    private void viewPastOrder(String user_id) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Your Order History");
        List<Order> orderHist = orderService.userOrders(user.getId());

//        Streams (learned in class 5/31) Will list orders by price
        List<Order> highToLow = orderHist.stream().sorted(Comparator.comparingInt(Order::getPrice)).collect(Collectors.toList());
        List<Order> lowToHigh = orderHist.stream().sorted(Comparator.comparingInt(Order::getPrice)).collect(Collectors.toList());

        exit:
        {
            while(true) {
                System.out.println("[1] View orders price Low to High");
                System.out.println("[2] View orders price High to Low");
                System.out.println("[x] Back");

                System.out.println("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        lowToHigh.forEach(System.out::println);
                        break;
                    case "2":
                        highToLow.forEach(System.out::println);
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

    private void updateProfile() {
    }

//    private
}



