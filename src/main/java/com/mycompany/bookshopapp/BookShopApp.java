// BookShopApp.java
package com.mycompany.bookshopapp;

import java.util.List;
import java.util.Scanner;

public class BookShopApp {
    public static void main(String[] args) {
        // Step 1: Create a scanner for user input
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

        // Step 6: Load inventory from file when the program starts
        bookInventory.loadInventoryFromFile("combined_data.txt", "revenue.txt"); // Provide both filenames

        // Step 7: Register a shutdown hook to save inventory to file on program exit
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            bookInventory.saveInventoryToFile("combined_data.txt", "revenue.txt"); // Adjust the filenames
        }));

        // Step 8: Display Main Menu and Handle User Choices
        int choice;
        do {
            displayMainMenu(bookInventory);
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
                        calculateTotalRevenueAndDisplay(bookInventory);
                        break;
                     case 5:
                        // Sort Books (Ascending)
                        BookSorter.sortBooksAscending(bookInventory.getAllBooks());
                        displayAllBooks(bookInventory.getAllBooks());
                        break;

                    case 6:
                        // Sort Books (Descending)
                        BookSorter.sortBooksDescending(bookInventory.getAllBooks());
                        displayAllBooks(bookInventory.getAllBooks());
                        break;

                    case 7:
                        // Display Most Expensive Book
                        BookStatistics.displayMostExpensiveBook(bookInventory.getAllBooks());
                        break;

                    case 8:
                        // Display Cheapest Book
                        BookStatistics.displayCheapestBook(bookInventory.getAllBooks());
                        break;

                    case 9:
                        // Exit
                        System.out.println("Thank you for using the Book Selling Shop!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 9);

        // Additional steps for saving inventory to a file can be added here
    }

    private static void displayMainMenu(BookInventory bookInventory) {
        System.out.println("\nMain Menu:");
        System.out.println("1. View All Books");
        System.out.println("2. Search for a Book");
        System.out.println("3. Sell a Book");
        System.out.println("4. Show Total Revenue");
        System.out.println("5. Sort Books (Ascending)");
        System.out.println("6. Sort Books (Descending)");
        System.out.println("7. Display Most Expensive Book");
        System.out.println("8. Display Cheapest Book");
        System.out.println("9. Exit");
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
                double totalRevenue = bookInventory.calculateTotalRevenue();
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

    // Method to calculate total revenue and display it
    private static void calculateTotalRevenueAndDisplay(BookInventory bookInventory) {
        double totalRevenue = calculateTotalRevenue(bookInventory);

        // Add revenue from the existing text file
        double existingTextFileRevenue = bookInventory.calculateRevenueFromTextFile("revenue.txt");
        totalRevenue += existingTextFileRevenue;

        System.out.println("Total Revenue: $" + totalRevenue);
    }
}