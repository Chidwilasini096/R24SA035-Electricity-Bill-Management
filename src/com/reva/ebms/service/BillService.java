package com.reva.ebms.service;

import com.reva.ebms.model.Consumer; // import statement across packages

/**
 * Service-layer class (second custom package: com.reva.ebms.service).
 * Contains the business logic that operates on arrays of Consumer objects.
 * Demonstrates: arrays of objects, loops, jump statements, String methods,
 * method overloading and static-method usage.
 */
public class BillService {

    /** METHOD OVERLOADING (version 1): show a single consumer's bill. */
    public void displayBill(Consumer c) {
        System.out.println(c); // implicitly calls the overridden toString()
    }

    /**
     * METHOD OVERLOADING (version 2): show every consumer in an array.
     * ARRAY OF OBJECTS + for-each loop + jump statement (continue).
     */
    public void displayBill(Consumer[] consumers, boolean skipZeroUsage) {
        for (Consumer c : consumers) {
            if (c == null) {
                continue; // JUMP STATEMENT: skip empty array slots
            }
            if (skipZeroUsage && c.getUnitsConsumed() == 0) {
                continue; // JUMP STATEMENT: skip consumers with no usage this month
            }
            displayBill(c);
        }
    }

    /**
     * Linear search through the array using STRING METHODS (trim, equalsIgnoreCase).
     * Uses an early RETURN (jump statement) once a match is found.
     */
    public Consumer searchByNumber(Consumer[] consumers, String number) {
        String target = number.trim(); // String method: trim
        for (Consumer c : consumers) {
            if (c == null) {
                continue;
            }
            if (c.getConsumerNumber().trim().equalsIgnoreCase(target)) { // String method: equalsIgnoreCase
                return c; // RETURN as soon as found
            }
        }
        return null;
    }

    /** Uses a plain for-loop (index based) and accumulator to total revenue. */
    public double totalRevenue(Consumer[] consumers) {
        double total = 0.0;
        for (int i = 0; i < consumers.length; i++) {
            if (consumers[i] == null) {
                continue;
            }
            total += consumers[i].calculateBill();
        }
        return total;
    }

    /**
     * Classifies consumers by their runtime type using a switch statement,
     * demonstrating BREAK as a jump statement and String.contains as a
     * SEARCH-style String method.
     */
    public void classifyConsumers(Consumer[] consumers) {
        int domestic = 0, commercial = 0, industrial = 0, others = 0;

        for (Consumer c : consumers) {
            if (c == null) {
                continue;
            }
            String className = c.getClass().getSimpleName();

            switch (className) {
                case "DomesticConsumer":
                    domestic++;
                    break; // JUMP STATEMENT: break
                case "CommercialConsumer":
                    commercial++;
                    break;
                case "IndustrialConsumer":
                    industrial++;
                    break;
                default:
                    others++;
            }
        }

        System.out.println("---- Consumer Classification ----");
        System.out.println("Domestic   : " + domestic);
        System.out.println("Commercial : " + commercial);
        System.out.println("Industrial : " + industrial);
        if (others > 0) {
            System.out.println("Unclassified: " + others);
        }
    }

    /**
     * Extra String-method demonstration: SEARCH (indexOf/contains) and
     * COMPARE (compareTo) used to validate a consumer number's format.
     */
    public boolean isValidConsumerNumber(String number) {
        String cleaned = number.trim().toUpperCase();
        boolean hasKnownPrefix = cleaned.contains("DC") || cleaned.contains("CC") || cleaned.contains("IC"); // search
        boolean lengthOk = cleaned.compareTo("AAA000") >= 0; // compare (lexicographic compareTo)
        return hasKnownPrefix && lengthOk;
    }

    /**
     * Splits a full name typed at the console into first/last parts.
     * Demonstrates the String methods: trim() and split().
     */
    public void printNameParts(String fullName) {
        String[] parts = fullName.trim().split("\\s+"); // String methods: trim, split
        System.out.print("  -> Parsed name: First = " + parts[0]);
        if (parts.length > 1) {
            System.out.println(", Last = " + parts[parts.length - 1]);
        } else {
            System.out.println(" (no last name given)");
        }
    }
}
