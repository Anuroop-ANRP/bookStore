package com.bookNet.bookNet.admin.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BookUpdateRequest {

    long bookId;

    String bookName;

    String authorName;

    String bookTitle;

    String description;

    String category;

    double price;

    int totalStock;

    public BookUpdateRequest() {
        // Default constructor
    }

    public BookUpdateRequest(long bookId, String bookName, String authorName, String bookTitle, String description, String category, double price, int totalStock) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookTitle = bookTitle;
        this.description = description;
        this.category = category;
        this.price = price;
        this.totalStock = totalStock;
    }
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }
}
