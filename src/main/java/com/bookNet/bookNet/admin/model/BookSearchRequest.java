package com.bookNet.bookNet.admin.model;

import com.bookNet.bookNet.common.SearchCondition;
import org.springframework.stereotype.Component;

@Component
public class BookSearchRequest {

    private String bookName;

    private String authorName;

    private String bookTitle;

    public BookSearchRequest() {
    }

    private String category;

    private int availableStock;

    private String searchCondition;

    public BookSearchRequest(String bookName, String authorName, String bookTitle, String category, int availableStock, String searchCondition) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookTitle = bookTitle;
        this.category = category;
        this.availableStock = availableStock;
        this.searchCondition = searchCondition;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }
}
