package com.mycompany.bookshopapp;

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

    private static Book findMostExpensiveBook(List<Book> books) {
        Book mostExpensiveBook = books.get(0);

        for (Book book : books) {
            if (book.getPrice() > mostExpensiveBook.getPrice()) {
                mostExpensiveBook = book;
            }
        }

        return mostExpensiveBook;
    }

    private static Book findCheapestBook(List<Book> books) {
        Book cheapestBook = books.get(0);

        for (Book book : books) {
            if (book.getPrice() < cheapestBook.getPrice()) {
                cheapestBook = book;
            }
        }

        return cheapestBook;
    }

    private static void displayBookDetails(Book book) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Genre: " + book.getGenre());
        System.out.println("Price: $" + book.getPrice());
        System.out.println("Quantity in Stock: " + book.getQuantityInStock());
    }
}
