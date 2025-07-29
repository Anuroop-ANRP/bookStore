package com.bookNet.bookNet.admin.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Data
public class BookRequest {

    String bookName;

    String authorName;

    String bookTitle;

    String description;

    String category;

    double price;

    int totalStock;

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getTotalStock() {
        return totalStock;
    }
}
