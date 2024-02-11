/*
 * Author: Tanvir Chowdhury
 * ID: 12246034
 */

package com.mycompany.bookshopapp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookSorter {

    // Sort books in ascending order based on their titles
    public static void sortBooksAscending(List<Book> books) {
        Collections.sort(books);
    }

    // Sort books in descending order based on their titles
    public static void sortBooksDescending(List<Book> books) {
        Collections.sort(books, Comparator.reverseOrder());
    }

    // Custom Comparator for sorting books based on titles
    private static class TitleComparator implements Comparator<Book> {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.getTitle().compareToIgnoreCase(book2.getTitle());
        }
    }

    // Example usage of the custom Comparator for sorting books based on titles
    public static void sortBooksCustom(List<Book> books) {
        Collections.sort(books, new TitleComparator());
    }
}
