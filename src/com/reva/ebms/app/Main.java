package com.reva.ebms.app;

import com.reva.ebms.model.CommercialConsumer;
import com.reva.ebms.model.Consumer;
import com.reva.ebms.model.ConnectionType;
import com.reva.ebms.model.DomesticConsumer;
import com.reva.ebms.model.IndustrialConsumer;
import com.reva.ebms.model.Payable;
import com.reva.ebms.service.BillService;

import java.util.Scanner;

/**
 * Third custom package: com.reva.ebms.app
 * Console-driven entry point of the Electricity Bill Management System.
 */
public class Main {

    // Array capacity is fixed for this console app -> instance/local scope demo below.
    private static final int MAX_CONSUMERS = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BillService billService = new BillService();

        // ARRAY OF OBJECTS
        Consumer[] consumers = new Consumer[MAX_CONSUMERS];
        int nextSlot; // LOCAL variable scope: only exists inside main()

        consumers[0] = new DomesticConsumer("Ravi Kumar", "DC001", 90);
        consumers[1] = new DomesticConsumer("Sita Devi", "DC002", 150);
        consumers[2] = new CommercialConsumer("Green Mart", "CC001", 400);
        consumers[3] = new CommercialConsumer("City Bakery", "CC002", 250);
        consumers[4] = new IndustrialConsumer("Steel Works Pvt Ltd", "IC001", 2000);
        nextSlot = 5;

        // OPERATOR PRECEDENCE demonstration: '*' and '/' evaluate before '+' and '-'
        double precedenceDemo = 10 + 5 * 2 - 6 / 3;
        System.out.println("Operator precedence demo (10 + 5*2 - 6/3) = " + precedenceDemo);
        System.out.println("Welcome to the Electricity Bill Management System\n");

        int choice;
        do {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Display all bills");
            System.out.println("2. Search consumer by number");
            System.out.println("3. Add a new consumer");
            System.out.println("4. Total revenue & consumer count");
            System.out.println("5. Polymorphism demo (interface + parent reference)");
            System.out.println("6. Classify consumers by type");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");

            // String methods (trim) + casting text to int
            String rawInput = sc.nextLine().trim();
            int parsedChoice;
            try {
                parsedChoice = Integer.parseInt(rawInput);
            } catch (NumberFormatException e) {
                parsedChoice = -1; // sentinel for invalid input
            }
            choice = parsedChoice;

            switch (choice) {
                case 1:
                    billService.displayBill(consumers, false);
                    break;

                case 2:
                    System.out.print("Enter consumer number to search: ");
                    String searchNum = sc.nextLine();
                    Consumer found = billService.searchByNumber(consumers, searchNum);
                    if (found != null) {
                        System.out.println("Found -> " + found); // toString() override in action
                    } else {
                        System.out.println("No consumer with that number was found.");
                    }
                    break;

                case 3:
                    if (nextSlot >= MAX_CONSUMERS) {
                        System.out.println("Consumer list is full. Cannot add more.");
                        break;
                    }
                    System.out.print("Enter consumer name: ");
                    String name = sc.nextLine();
                    billService.printNameParts(name); // split()/trim() String methods

                    System.out.print("Enter consumer number (e.g. DC003): ");
                    String number = sc.nextLine().trim();
                    if (!billService.isValidConsumerNumber(number)) {
                        System.out.println("Warning: consumer number does not match the expected DC/CC/IC pattern.");
                    }

                    System.out.print("Enter units consumed: ");
                    int units = Integer.parseInt(sc.nextLine().trim());

                    System.out.println("Select connection type: 1-Domestic  2-Commercial  3-Industrial");
                    System.out.print("Your choice: ");
                    int typeChoice = Integer.parseInt(sc.nextLine().trim());

                    Consumer newConsumer;
                    if (typeChoice == 1) {
                        newConsumer = new DomesticConsumer(name, number, units);
                    } else if (typeChoice == 2) {
                        newConsumer = new CommercialConsumer(name, number, units);
                    } else if (typeChoice == 3) {
                        newConsumer = new IndustrialConsumer(name, number, units);
                    } else {
                        System.out.println("Invalid type. Consumer not added.");
                        break;
                    }
                    consumers[nextSlot] = newConsumer;
                    nextSlot++;
                    System.out.println("Added successfully: " + newConsumer);
                    break;

                case 4:
                    double revenue = billService.totalRevenue(consumers);
                    int roundedRevenue = (int) revenue; // TYPE CASTING: double -> int
                    System.out.printf("Total Revenue     : Rs. %.2f%n", revenue);
                    System.out.println("Rounded (int cast): Rs. " + roundedRevenue);
                    System.out.println("Total Consumers Ever Created: " + Consumer.getTotalConsumers()); // static method
                    break;

                case 5:
                    // POLYMORPHISM: a parent-type/interface reference holding a child object.
                    Consumer parentRef = consumers[4];              // dynamic binding via base class ref
                    Payable interfaceRef = consumers[0];            // interface reference
                    System.out.println("Parent-ref .toString() -> " + parentRef);
                    System.out.printf("Interface-ref .calculateBill() -> Rs. %.2f%n", interfaceRef.calculateBill());
                    System.out.println("Connection type via enum: " + parentRef.getConnectionType());
                    break;

                case 6:
                    billService.classifyConsumers(consumers);
                    break;

                case 7:
                    System.out.println("Exiting. Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 7.");
            }
        } while (choice != 7); // do-while LOOP + control-flow

        sc.close();
    }
}
