package com.mycompany.bookshopapp;

import java.util.Comparator;
import java.util.List;

public class BookStatistics {

    public static void displayMostExpensiveBook(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books available to calculate statistics.");
            return;
        }

        Book mostExpensiveBook = findMostExpensiveBook(books);

        System.out.println("\nMost Expensive Book:");
        displayBookDetails(mostExpensiveBook);
    }

    public static void displayCheapestBook(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books available to calculate statistics.");
            return;
        }

        Book cheapestBook = findCheapestBook(books);

        System.out.println("\nCheapest Book:");
        displayBookDetails(cheapestBook);
    }

    public static void displayBookWithMostRevenue(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books available to calculate statistics.");
            return;
        }

        Book bookWithMostRevenue = findBookWithMostRevenue(books);

        System.out.println("\nBook with Most Revenue:");
        displayBookDetails(bookWithMostRevenue);
    }

    public static void displayBookWithLeastRevenue(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books available to calculate statistics.");
            return;
        }

        Book bookWithLeastRevenue = findBookWithLeastRevenue(books);

        System.out.println("\nBook with Least Revenue:");
        displayBookDetails(bookWithLeastRevenue);
    }

    private static Book findMostExpensiveBook(List<Book> books) {
        return books.stream()
                .max(Comparator.comparingDouble(Book::getPrice))
                .orElse(null);
    }

    private static Book findCheapestBook(List<Book> books) {
        return books.stream()
                .min(Comparator.comparingDouble(Book::getPrice))
                .orElse(null);
    }

    private static Book findBookWithMostRevenue(List<Book> books) {
        return books.stream()
                .max(Comparator.comparingDouble(Book::getTotalRevenue))
                .orElse(null);
    }

    private static Book findBookWithLeastRevenue(List<Book> books) {
        return books.stream()
                .min(Comparator.comparingDouble(Book::getTotalRevenue))
                .orElse(null);
    }

    private static void displayBookDetails(Book book) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Genre: " + book.getGenre());
        System.out.println("Price: $" + book.getPrice());
        System.out.println("Quantity in Stock: " + book.getQuantityInStock());
        System.out.println("Total Revenue: $" + book.getTotalRevenue());
    }
}
