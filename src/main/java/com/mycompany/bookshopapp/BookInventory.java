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
    
    public double calculateTotalRevenue() {
        double totalRevenue = 0;

        for (Book book : books) {
            totalRevenue += book.getTotalRevenue();
        }

        return totalRevenue;
    }

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
