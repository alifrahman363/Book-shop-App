// BookInventory.java
package com.mycompany.bookshopapp;

import java.io.*;
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

    public void loadInventoryFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(",");
                if (bookData.length == 5) {
                    String title = bookData[0].trim();
                    String author = bookData[1].trim();
                    String genre = bookData[2].trim();
                    double price = Double.parseDouble(bookData[3].trim());
                    int quantityInStock = Integer.parseInt(bookData[4].trim());

                    Book book = new Book(title, author, genre, price, quantityInStock);
                    addBook(book);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing inventory file found. Starting with an empty inventory.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveInventoryToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Book book : books) {
                writer.println(book.getTitle() + "," + book.getAuthor() + "," +
                        book.getGenre() + "," + book.getPrice() + "," + book.getQuantityInStock());
            }
            System.out.println("Inventory saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving inventory to file: " + e.getMessage());
        }
    }
    
//        public void saveInventoryToFile(String filename) {
//            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
//                for (Book book : books) {
//                    String bookData = String.format("%s,%s,%s,%.2f,%d",
//                            book.getTitle(), book.getAuthor(), book.getGenre(),
//                            book.getPrice(), book.getQuantityInStock());
//                    writer.println(bookData);
//                }
//                System.out.println("Inventory saved to file: " + filename);
//            } catch (IOException e) {
//                System.out.println("Error saving inventory to file: " + e.getMessage());
//            }
//        }


}
