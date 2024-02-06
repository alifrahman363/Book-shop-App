// BookShopApp.java
package com.mycompany.bookshopapp;

import java.util.List;
import java.util.Scanner;

public class BookShopApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 2: Display Welcome Message
        System.out.println("Welcome to the Book Selling Shop!");

        // Step 3: Input N (Number of Books to Manage)
        System.out.print("Enter the number of books to manage (N): ");
        int numberOfBooks = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Step 4: Initialize Book Inventory
        BookInventory bookInventory = new BookInventory();

        // Step 5: Input Book Details and Add to Inventory
        for (int i = 1; i <= numberOfBooks; i++) {
            System.out.println("Enter details for Book #" + i + ":");
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Author: ");
            String author = scanner.nextLine();
            System.out.print("Genre: ");
            String genre = scanner.nextLine();
            System.out.print("Price: ");
            double price = scanner.nextDouble();
            System.out.print("Quantity in Stock: ");
            int quantityInStock = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Book book = new Book(title, author, genre, price, quantityInStock);
            bookInventory.addBook(book);
        }

        // Load inventory from file when the program starts
        bookInventory.loadInventoryFromFile("inventory.txt");

        // Add a shutdown hook to save the inventory to file when the program exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            bookInventory.saveInventoryToFile("inventory.txt");
        }));

        // Step 8: Display Main Menu
        int choice;
        do {
            displayMainMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // View All Books
                    displayAllBooks(bookInventory.getAllBooks());
                    break;
                case 2:
                    // Search for a Book
                    searchForBook(scanner, bookInventory);
                    break;
                case 3:
                    // Sell a Book
                    sellBook(scanner, bookInventory);
                    break;
                case 4:
                    // Calculate Total Revenue
                    calculateTotalRevenue(bookInventory);
                    break;
                case 5:
                    // Exit
                    System.out.println("Thank you for using the Book Selling Shop!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        // Additional steps for saving inventory to a file can be added here
    }

    private static void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. View All Books");
        System.out.println("2. Search for a Book");
        System.out.println("3. Sell a Book");
        System.out.println("4. Calculate Total Revenue");
        System.out.println("5. Exit");
    }

    private static void displayAllBooks(List<Book> books) {
        System.out.println("\nAll Books in Inventory:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() +
                    " | Genre: " + book.getGenre() +
                    " | Price: $" + book.getPrice() +
                    " | Quantity in Stock: " + book.getQuantityInStock());
        }
    }

    private static void searchForBook(Scanner scanner, BookInventory bookInventory) {
        System.out.print("Enter the book title or author to search: ");
        String searchQuery = scanner.nextLine();
        List<Book> matchingBooks = bookInventory.searchBooks(searchQuery);

        if (!matchingBooks.isEmpty()) {
            System.out.println("Matching Books:");
            for (Book book : matchingBooks) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() +
                        " | Genre: " + book.getGenre() +
                        " | Price: $" + book.getPrice() +
                        " | Quantity in Stock: " + book.getQuantityInStock());
            }
        } else {
            System.out.println("No matching books found.");
        }
    }

    private static void sellBook(Scanner scanner, BookInventory bookInventory) {
        System.out.print("Enter the title of the book you want to sell: ");
        String sellTitle = scanner.nextLine();

        List<Book> matchingBooks = bookInventory.searchBooks(sellTitle);

        if (!matchingBooks.isEmpty()) {
            Book bookToSell = matchingBooks.get(0);

            if (bookToSell.getQuantityInStock() > 0) {
                // Book is in stock
                System.out.println("Sale Details:");
                System.out.println("Title: " + bookToSell.getTitle());
                System.out.println("Author: " + bookToSell.getAuthor());
                System.out.println("Price: $" + bookToSell.getPrice());

                // Update quantity sold
                System.out.print("Enter the quantity to sell: ");
                int soldQuantity = scanner.nextInt();
                bookToSell.setQuantitySold(soldQuantity);

                // Calculate and display total revenue
                double salePrice = bookToSell.getPrice() * soldQuantity;
                double totalRevenue = salePrice + calculateTotalRevenue(bookInventory);

                System.out.println("Book sold successfully!");
                System.out.println("Remaining quantity in stock: " + bookToSell.getQuantityInStock());
                System.out.println("Total Revenue: $" + totalRevenue);
            } else {
                System.out.println("Sorry, the book is out of stock.");
            }
        } else {
            System.out.println("Book not found. Unable to sell.");
        }
    }

    private static double calculateTotalRevenue(BookInventory bookInventory) {
        double totalRevenue = 0;

        for (Book book : bookInventory.getAllBooks()) {
            int soldQuantity = book.getOriginalQuantityInStock() - book.getQuantityInStock();
            if (soldQuantity > 0) {
                totalRevenue += (book.getPrice() * soldQuantity);
            }
        }

        return totalRevenue;
    }
}
