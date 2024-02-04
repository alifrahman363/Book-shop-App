package com.mycompany.bookshopapp;

public class Book {
    private String title;
    private String author;
    private String genre;
    private double price;
    private int quantityInStock;
    private double totalRevenue; // New field to store total revenue

    public Book(String title, String author, String genre, double price, int quantityInStock) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.totalRevenue = 0; // Initialize total revenue to zero
    }

    // Getters and setters

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

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void addToTotalRevenue(double revenue) {
        this.totalRevenue += revenue;
    }

    public void setQuantitySold(int quantitySold) {
        // Additional validation or logic can be added if needed
        if (quantitySold > 0 && quantitySold <= quantityInStock) {
            this.quantityInStock -= quantitySold;
            addToTotalRevenue(price * quantitySold);
        } else {
            System.out.println("Invalid quantity sold. Unable to update.");
        }
    }
}
