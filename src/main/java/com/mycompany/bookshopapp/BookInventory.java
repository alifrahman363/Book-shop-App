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
}
