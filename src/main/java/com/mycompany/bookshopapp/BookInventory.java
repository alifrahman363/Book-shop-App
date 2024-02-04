/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshopapp;

/**
 *
 * @author alif
 */
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

    // Other methods for searching, selling, calculating revenue, etc.

    public List<Book> searchBooks(String searchQuery) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            // Check if the searchQuery matches the book title or author (case-insensitive)
            if (book.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(searchQuery.toLowerCase())) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }
}
