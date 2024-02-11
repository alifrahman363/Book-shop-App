/*
 * Author: Tanvir Chowdhury
 * ID: 12246034
 */

package com.mycompany.bookshopapp;

import java.util.Scanner;

public class BookEditor {
    // Class for editing book details

    public static void editBookDetails(Scanner scanner, BookInventory bookInventory) {
        System.out.print("Enter the title of the book you want to edit: ");
        String editTitle = scanner.nextLine();

        // Search for the book to edit
        Book bookToEdit = findBookByTitle(editTitle, bookInventory);

        if (bookToEdit != null) {
            System.out.println("Current Details for " + bookToEdit.getTitle() + ":");
            displayBookDetails(bookToEdit);

            // Get new details from the user
            System.out.println("Enter new details:");

            System.out.print("Title: ");
            String newTitle = scanner.nextLine();
            bookToEdit.setTitle(newTitle);

            System.out.print("Author: ");
            String newAuthor = scanner.nextLine();
            bookToEdit.setAuthor(newAuthor);

            System.out.print("Genre: ");
            String newGenre = scanner.nextLine();
            bookToEdit.setGenre(newGenre);

            System.out.print("Price: ");
            double newPrice = scanner.nextDouble();
            bookToEdit.setPrice(newPrice);

            System.out.print("Quantity in Stock: ");
            int newQuantityInStock = scanner.nextInt();
            bookToEdit.setQuantityInStock(newQuantityInStock);

            System.out.println("Book details updated successfully:");
            displayBookDetails(bookToEdit);
        } else {
            System.out.println("Book not found. Unable to edit.");
        }
    }

    private static Book findBookByTitle(String title, BookInventory bookInventory) {
        for (Book book : bookInventory.getAllBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    private static void displayBookDetails(Book book) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Genre: " + book.getGenre());
        System.out.println("Price: $" + book.getPrice());
        System.out.println("Quantity in Stock: " + book.getQuantityInStock());
    }
}
