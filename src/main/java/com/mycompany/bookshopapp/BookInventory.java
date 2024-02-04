package com.mycompany.bookshopapp;

import java.util.ArrayList;
import java.util.List;

public class BookInventory {
    private List<Book> books;

    public BookInventory() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Book> searchBooks(String searchQuery) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(searchQuery.toLowerCase())) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Book book : books) {
            totalRevenue += book.getTotalRevenue();
        }
        return totalRevenue;
    }

    public void displayBookDetails(String title) {
        List<Book> matchingBooks = searchBooks(title);

        if (!matchingBooks.isEmpty()) {
            Book book = matchingBooks.get(0);

            System.out.println("Book Details:");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("Price: $" + book.getPrice());
            System.out.println("Quantity in Stock: " + book.getQuantityInStock());
            System.out.println("Total Revenue: $" + book.getTotalRevenue());
        } else {
            System.out.println("Book not found.");
        }
    }
}
