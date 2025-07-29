package com.bookNet.bookNet.admin.service;

import com.bookNet.bookNet.admin.helper.SearchSpecifications;
import com.bookNet.bookNet.admin.model.BookRequest;
import com.bookNet.bookNet.admin.model.BookSearchRequest;
import com.bookNet.bookNet.admin.model.BookUpdateRequest;
import com.bookNet.bookNet.admin.repository.BookInventoryRepository;
import com.bookNet.bookNet.common.SearchCondition;
import com.bookNet.bookNet.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;

import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookInventoryService {

    @Autowired
    private BookInventoryRepository bookInventoryRepository;


    public Page<Book> getAllBooks(int page, int size) {

        PageRequest pageable = PageRequest.of(page, size);
        Page<Book> bookList = bookInventoryRepository.findAll(pageable);


        System.out.println("Fetching all books from inventory");


        return bookList;
    }

    public List<Book> addBook(List<BookRequest> book) {

        List<Book> newBook = new ArrayList<>();

        book.forEach(bookRequest -> newBook.add(getBookDetails(bookRequest)));


        return bookInventoryRepository.saveAll(newBook);
    }



    private Book getBookDetails(BookRequest bookRequest) {
        Book newBook = new Book();

        newBook.setBookTitle(bookRequest.getBookTitle());
        newBook.setAuthorName(bookRequest.getAuthorName());
        newBook.setPrice(bookRequest.getPrice());
        newBook.setRemainingStock(bookRequest.getTotalStock());
        newBook.setTotalStock(bookRequest.getTotalStock());
        newBook.setBookName(bookRequest.getBookName());
        newBook.setDescription(bookRequest.getDescription());
        newBook.setRegistrationDateTime(new Time(System.currentTimeMillis()));
        newBook.setCategory(bookRequest.getCategory());
        newBook.setUpdatedDateTime(new Time(System.currentTimeMillis()));
        newBook.setLastUpdateBy("Admin"); // Assuming the user is Admin for now

        return newBook;
    }

    private Book getBookDetails(BookUpdateRequest bookRequest) {
        Book newBook = new Book();

        newBook.setBookTitle(bookRequest.getBookTitle());
        newBook.setAuthorName(bookRequest.getAuthorName());
        newBook.setPrice(bookRequest.getPrice());
        newBook.setRemainingStock(bookRequest.getTotalStock());
        newBook.setTotalStock(bookRequest.getTotalStock());
        newBook.setBookName(bookRequest.getBookName());
        newBook.setDescription(bookRequest.getDescription());
        newBook.setCategory(bookRequest.getCategory());
        newBook.setUpdatedDateTime(new Time(System.currentTimeMillis()));
        newBook.setLastUpdateBy("Admin"); // Assuming the user is Admin for now

        return newBook;
    }


    public void deleteBook(Long bookId) {

         bookInventoryRepository.deleteById(bookId);
    }

    public Book updateBook(BookUpdateRequest book) {

        Book existingBook = bookInventoryRepository.findById(book.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + book.getBookId()));

        Book updateBook = getBookDetails(book);
        updateBook.setBookId(existingBook.getBookId());
        updateBook.setRegistrationDateTime(existingBook.getRegistrationDateTime());



        return bookInventoryRepository.save(updateBook);
    }

    public Page<Book> getAllBooksBySearch(int page, int size, BookSearchRequest searchRequest) {

        PageRequest pageable = PageRequest.of(page, size);
        Page<Book> bookList;

        SearchCondition searchCondition = SearchCondition.valueOf(searchRequest.getSearchCondition().toUpperCase());

        System.out.println(searchRequest.getCategory() + "  " + searchRequest.getAvailableStock());

        Specification<Book> spec = (root, query, criteriaBuilder) -> {

            Specification<Book> bookNameSpec = SearchSpecifications.hasBookName(searchRequest.getBookName());
            Specification<Book> authorNameSpec = SearchSpecifications.hasAuthorName(searchRequest.getAuthorName());
            Specification<Book> bookTitleSpec = SearchSpecifications.hasBookTitle(searchRequest.getBookTitle());
            Specification<Book> categorySpec = SearchSpecifications.hasCategory(searchRequest.getCategory());
            Specification<Book> availableStockSpec = SearchSpecifications.hasAvailableStock(searchRequest.getAvailableStock(), searchCondition);
            return criteriaBuilder.and(
                    bookNameSpec.toPredicate(root, query, criteriaBuilder),
                    authorNameSpec.toPredicate(root, query, criteriaBuilder),
                    bookTitleSpec.toPredicate(root, query, criteriaBuilder),
                    categorySpec.toPredicate(root, query, criteriaBuilder),
                    availableStockSpec.toPredicate(root, query, criteriaBuilder)
            );
        };

        bookList = bookInventoryRepository.findAll(spec, pageable);

        return bookList;

    }

    public boolean addBulkData(MultipartFile file) {

        validateFile(file);
        try {
            // Assuming a method to parse CSV and save books
            List<Book> books = parseCsvFile(file);
            bookInventoryRepository.saveAll(books);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage());
        }

    }

    private List<Book> parseCsvFile(MultipartFile file) {

        List<Book> books = new ArrayList<>();

        try(CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] values;
            boolean isFirstLine = true;

            while ((values = csvReader.readNext()) != null) {

                if (values.length < 7) {
                    throw new RuntimeException("Invalid CSV format. Each row must have at least 7 columns.");
                }

                if (isFirstLine) {https://github.com/Anuroop-ANRP/bookStore
                    isFirstLine = false; // Skip header line
                    continue;
                }

                Book book = new Book();
                book.setBookName(values[0]);
                book.setAuthorName(values[1]);
                book.setBookTitle(values[2]);
                book.setDescription(values[3]);
                book.setCategory(values[4]);
                book.setPrice(Double.parseDouble(values[5]));
                book.setTotalStock(Integer.parseInt(values[6]));
                book.setRemainingStock(book.getTotalStock());
                book.setRegistrationDateTime(new Time(System.currentTimeMillis()));
                book.setUpdatedDateTime(new Time(System.currentTimeMillis()));
                book.setLastUpdateBy("Admin"); // Assuming the user is Admin for now

                books.add(book);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV file: " + e.getMessage());

        }

        return books;
    }

    private void validateFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.endsWith(".csv")) {
            throw new RuntimeException("Invalid file format. Please upload a CSV file.");
        }

        // Additional validation logic can be added here, such as checking file size, etc.
    }
}
