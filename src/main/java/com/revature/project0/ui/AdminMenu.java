package com.revature.project0.ui;

//models
import com.revature.project0.models.Store;
import com.revature.project0.models.User;
import com.revature.project0.models.Product;
//services
import com.revature.project0.services.ProductService;

//utils
import com.revature.project0.services.StoreService;
import com.revature.project0.util.annotations.Inject;


//Java Libs
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public class AdminMenu implements IMenu {

    @Inject
    private final User user;
    private final ProductService productService;
    private final StoreService storeService;


    @Inject
    public AdminMenu(User user, ProductService productService, StoreService storeService) {

        this.user = user;
        this.productService = productService;
        this.storeService = storeService;
    }


    @Override
    public void start() {

        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nWelcome to the Admin Menu");
                System.out.println("[1] Create an Item");
                System.out.println("[2] Replenish Item Inventory");
                System.out.println("[3] Create a Store");
                System.out.println("[4] Update a Store");
                System.out.println("[5] View all Orders");
                System.out.println("[6] View Orders by Store");
                System.out.println("[7] View Orders by User");
                System.out.println("[x] Sign out");

                System.out.println("Enter: ");

                switch (scan.nextLine()) {
                    case "1":
                        createItem();
                        break;
                    case "2":
                        replenishInventory();
                        break;
                    case "3":
                        createStore();
                        break;
                    case "4":
                        updateStore();
                        break;
                    case "5":
                        viewAllOrders();
                        break;
                    case "6":
                        viewOrdersByStore();
                        break;
                    case "7":
                        viewOrdersByUser();
                        break;
                    case "x":
                        break exit;
                    default:
                        System.out.println("\nInvalid input, please try again!");
                        break;
                }
            }
        }
    }

    private void createItem() {
        Scanner scan = new Scanner(System.in);
        Product prod = new Product();

        exit:
        {
            while(true) {
                System.out.println("\nCreating a new Product...");

                prod.setId(UUID.randomUUID().toString());

                System.out.print("\nProduct Name: ");
                prod.setName(scan.nextLine());

                System.out.print("\nProduct Description: ");
                prod.setDescription(scan.nextLine());

                System.out.print("\nProduct Price: ");
                prod.setPrice(Integer.parseInt(scan.nextLine()));

                System.out.print("\nStarting Quantity: ");
                prod.setQuantity(Integer.parseInt(scan.nextLine()));

                System.out.print("\nProduct Category: ");
                prod.setCategory_id(scan.nextLine());

                System.out.println("\nPlease confirm Product (y/n)");

                System.out.println("Product Name: " + prod.getName());
                System.out.println("Product Description: " + prod.getDescription());
                System.out.println("Product Price: " + prod.getPrice());
                System.out.println("Product Quantity: " + prod.getQuantity());


                System.out.println("Enter: ");
                String input = scan.nextLine();


                switch (input) {
                    case "y":
                        productService.register(prod);
                        break exit;
                    case "n":
                        break;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }

    private void replenishInventory() {
        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.println("Please select the product");

            List<Product> prods = productService.getAllProd();

            for (int i = 0; i < prods.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + prods.get(i).getName());
            }

            System.out.print("Enter: ");
            int input = scan.nextInt() - 1;
            scan.nextLine();

            if (input >= 0 && input < prods.size()) {
                Product selectedProd = prods.get(input);

                System.out.print("\nNew amount: ");
                String quantity = scan.nextLine();

                if (productService.updateQuant(Integer.parseInt(quantity), selectedProd.getId())) {
                    System.out.println("\nUpdate was successful!");
                    break;
                }
            } else System.out.println("\nInvalid product selection!");
        }

    }

    private void createStore() {
        Scanner scan = new Scanner(System.in);
        Store store = new Store();

        exit:
        {
            while (true) {
                System.out.println("Creating a store...");

                store.setId(UUID.randomUUID().toString());

                System.out.print("Location: ");
                store.setLocation(scan.nextLine());

                System.out.println("\nPlease confirm store (y/n)");
                System.out.println("Store Id: " + store.getId());
                System.out.println("Store Location: " + store.getLocation());

                switch (scan.nextLine()) {
                    case "y":
                        storeService.register(store);
                        break exit;
                    case "n":
                        break;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }


    private void updateStore() {}

    private void viewAllOrders() {}

    private void viewOrdersByStore() {}
    private void viewOrdersByUser() {}
}