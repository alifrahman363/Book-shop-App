// Book.java
package com.mycompany.bookshopapp;

public class Book {
    private String title;
    private String author;
    private String genre;
    private double price;
    private int quantityInStock;
    private double totalRevenue;
    private int originalQuantityInStock;

    public Book(String title, String author, String genre, double price, int quantityInStock) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.originalQuantityInStock = quantityInStock;
        this.totalRevenue = 0;
    }

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
        int remainingStock = this.quantityInStock - quantitySold;
        if (quantitySold > 0 && remainingStock >= 0) {
            this.quantityInStock = remainingStock;
            addToTotalRevenue(price * quantitySold);
        } else {
            System.out.println("Invalid quantity sold. Unable to update.");
        }
    }

    public int getOriginalQuantityInStock() {
        return originalQuantityInStock;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
