package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    // Method to load all products from inventory.csv into a HashMap
    public static HashMap<String, Product> loadInventory() {
        HashMap<String, Product> inventoryMap = new HashMap<>();
        String fileName = "inventory.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());

                    Product product = new Product(id, name, price);
                    // Add the product to the map using the product ID as the key
                    inventoryMap.put(id, product);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the inventory file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing price: " + e.getMessage());
        }

        return inventoryMap;
    }

    public static void main(String[] args) {
        // Load the inventory data into the HashMap
        HashMap<String, Product> inventory = loadInventory();
        Scanner scanner = new Scanner(System.in);
        String choice;

        System.out.println("--- Product Inventory Search (HashMap) ---");

        // Loop to handle the BONUS requirement for multiple repeated searches
        do {
            System.out.print("\nEnter a product ID to search: ");
            String searchId = scanner.nextLine().trim();

            // Perform an instant lookup using the map key
            if (inventory.containsKey(searchId)) {
                Product foundProduct = inventory.get(searchId);
                System.out.println("Product Found -> " + foundProduct);
            } else {
                System.out.println("Sorry, that product was not found.");
            }

            // BONUS: Ask the user if they want to repeat the search process
            System.out.print("\nDo you want to search again? (yes/no): ");
            choice = scanner.nextLine().trim().toLowerCase();

        } while (choice.equals("yes") || choice.equals("y"));

        System.out.println("\nThank you for using the inventory application. Goodbye!");
        scanner.close();
    }
}
