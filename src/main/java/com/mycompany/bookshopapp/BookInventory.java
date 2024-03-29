/*
 * Author: Tanvir Chowdhury
 * ID: 12246034
 */

package com.mycompany.bookshopapp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookInventory {
    // Class representing the inventory of books in the bookshop

    private List<Book> books;

    // Constructor to initialize the BookInventory instance
    public BookInventory() {
        this.books = new ArrayList<>();
    }

    // Method to add a book to the inventory
    public void addBook(Book book) {
        books.add(book);
    }

    // Method to get all books in the inventory
    public List<Book> getAllBooks() {
        return books;
    }

    // Method to search for books based on title or author
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

    // Method to load inventory data from files
    public void loadInventoryFromFile(String bookFilename, String revenueFilename) {
        try (BufferedReader bookReader = new BufferedReader(new FileReader(bookFilename));
             BufferedReader revenueReader = new BufferedReader(new FileReader(revenueFilename))) {

            String bookLine;
            String revenueLine;

            while ((bookLine = bookReader.readLine()) != null && (revenueLine = revenueReader.readLine()) != null) {
                String[] bookData = bookLine.split(",");
                String[] revenueData = revenueLine.split(",");

                if (bookData.length == 5 && revenueData.length == 2) {
                    String title = bookData[0].trim();
                    String author = bookData[1].trim();
                    String genre = bookData[2].trim();
                    double price = Double.parseDouble(bookData[3].trim());
                    int quantityInStock = Integer.parseInt(bookData[4].trim());

                    Book book = new Book(title, author, genre, price, quantityInStock);
                    double revenue = Double.parseDouble(revenueData[1].trim());
                    book.addToTotalRevenue(revenue);

                    addBook(book);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing inventory file found. Starting with an empty inventory.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to calculate total revenue from book sales
    public double calculateTotalRevenue() {
        double totalRevenue = 0;

        for (Book book : books) {
            int soldQuantity = book.getOriginalQuantityInStock() - book.getQuantityInStock();

            // Get revenue from the text file and add it to the total
            double existingRevenue = book.getRevenueFromFile("revenue.txt");
            totalRevenue += existingRevenue;

            if (soldQuantity > 0) {
                totalRevenue += (book.getPrice() * soldQuantity);
            }
        }

        return totalRevenue;
    }

    // Method to save inventory data to files
    public void saveInventoryToFile(String bookFilename, String revenueFilename) {
        try (PrintWriter bookWriter = new PrintWriter(new FileWriter(bookFilename));
             PrintWriter revenueWriter = new PrintWriter(new FileWriter(revenueFilename))) {

            for (Book book : books) {
                bookWriter.println(book.getTitle() + "," + book.getAuthor() + "," +
                        book.getGenre() + "," + book.getPrice() + "," + book.getQuantityInStock());

                // Save revenue data
                revenueWriter.println(book.getTitle() + "," + book.getTotalRevenue());
            }

            System.out.println("Inventory saved to file: " + bookFilename);
            System.out.println("Revenue data saved to file: " + revenueFilename);
        } catch (IOException e) {
            System.out.println("Error saving inventory or revenue data to file: " + e.getMessage());
        }
    }

    // Method to calculate total revenue from a text file
    public double calculateRevenueFromTextFile(String revenueFilename) {
        double totalRevenue = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(revenueFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] revenueData = line.split(",");
                if (revenueData.length == 2) {
                    totalRevenue += Double.parseDouble(revenueData[1].trim());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing revenue file found. ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalRevenue;
    }
}
