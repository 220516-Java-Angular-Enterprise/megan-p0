package com.revature.project0.ui;

//models
import com.revature.project0.models.User;
import com.revature.project0.models.Product;
//services
//utils
import com.revature.project0.util.annotations.Inject;


//Java Libs
import java.util.Scanner;
import java.util.UUID;


public class AdminMenu implements IMenu {

    @Inject
    private final User user;


    @Inject
    public AdminMenu(User user) {
        this.user = user;
    }


    @Override
    public void start() {

        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nWelcome to the Admin Menu");
                System.out.println("[1] Create an Item");
                System.out.println("[2] Update an Item");
                System.out.println("[3] Create a Store");
                System.out.println("[4] Update a Store");
                System.out.println("[5] View all Orders");
                System.out.println("[x] Sign out");

                System.out.print("\nEnter");

                switch (scan.nextLine()) {
                    case "1":
                        createItem();
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "5":
                        break;
                    case "x":
                        break;
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
            }
        }
    }
}