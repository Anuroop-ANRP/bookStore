package com.bookNet.bookNet.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


import java.sql.Time;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bookId;

    String bookName;

    String authorName;

    String bookTitle;

    String description;

    String category;

    double price;

    int remainingStock;

    int totalStock;

    Time registrationDateTime;

    Time updatedDateTime;

    String lastUpdateBy;

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRemainingStock(int remainingStock) {
        this.remainingStock = remainingStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public int getBookId() {
        return bookId;
    }

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

    public int getRemainingStock() {
        return remainingStock;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public Time getRegistrationDateTime() {
        return registrationDateTime;
    }

    public Time getUpdatedDateTime() {
        return updatedDateTime;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setRegistrationDateTime(Time registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

    public void setUpdatedDateTime(Time updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
}
