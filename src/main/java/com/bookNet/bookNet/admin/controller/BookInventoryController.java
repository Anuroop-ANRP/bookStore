package com.bookNet.bookNet.admin.controller;

import com.bookNet.bookNet.admin.model.BookRequest;
import com.bookNet.bookNet.admin.model.BookSearchRequest;
import com.bookNet.bookNet.admin.model.BookUpdateRequest;
import com.bookNet.bookNet.admin.service.BookInventoryService;
import com.bookNet.bookNet.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/booknet/inventory")
public class BookInventoryController {

    @Autowired
    private BookInventoryService bookInventoryService;

    @GetMapping
    public Page<Book> getBookInventory(@RequestParam int page, @RequestParam int size) {


        Page<Book> bookList = bookInventoryService.getAllBooks( page,  size);

        return bookList;
    }

    @PostMapping
    public List<Book> addBookToInventory(@RequestBody List<BookRequest> book) {

        return bookInventoryService.addBook(book);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBookFromInventory(@PathVariable Long bookId) {

        bookInventoryService.deleteBook(bookId);
    }

    @PutMapping
    public Book updateBookInInventory(@RequestBody BookUpdateRequest book) {

        return bookInventoryService.updateBook(book);
    }

    @PostMapping("/search")
    public Page<Book> getBookInventoryBySearch(@RequestParam int page, @RequestParam int size, @RequestBody BookSearchRequest searchRequest) {

        Page<Book> books = bookInventoryService.getAllBooksBySearch(page, size, searchRequest);

        return books;
    }

    @PostMapping("/book/bulk")
    public boolean addBulkData(@RequestParam("file") MultipartFile file) {


        return bookInventoryService.addBulkData(file);
    }
}
