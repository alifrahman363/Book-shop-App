// Book.java
package com.mycompany.bookshopapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Book implements Comparable<Book> {
    // Class representing a Book entity in the bookshop

    private String title;
    private String author;
    private String genre;
    private double price;
    private int quantityInStock;
    private double totalRevenue;
    private int originalQuantityInStock;

    // Constructor to initialize a Book instance
    public Book(String title, String author, String genre, double price, int quantityInStock) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.originalQuantityInStock = quantityInStock;
        this.totalRevenue = 0;
    }

    // Getter methods for retrieving book details
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    // Setter method to update quantity in stock
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    // Getter method for total revenue
    public double getTotalRevenue() {
        return totalRevenue;
    }

    // Method to add revenue from book sales
    public void addToTotalRevenue(double revenue) {
        this.totalRevenue += revenue;
    }

    // Method to set the quantity sold and update total revenue
    public void setQuantitySold(int quantitySold) {
        int remainingStock = this.quantityInStock - quantitySold;
        if (quantitySold > 0 && remainingStock >= 0) {
            this.quantityInStock = remainingStock;
            addToTotalRevenue(price * quantitySold);
        } else {
            System.out.println("Invalid quantity sold. Unable to update.");
        }
    }

    // Getter method for original quantity in stock
    public int getOriginalQuantityInStock() {
        return originalQuantityInStock;
    }

    // Method to read revenue data from a file
    public double getRevenueFromFile(String revenueFilename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(revenueFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] revenueData = line.split(",");
                if (revenueData.length == 2 && revenueData[0].trim().equals(this.title)) {
                    return Double.parseDouble(revenueData[1].trim());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing revenue file found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
     @Override
    public int compareTo(Book otherBook) {
        return this.title.compareToIgnoreCase(otherBook.getTitle());
    }
}
